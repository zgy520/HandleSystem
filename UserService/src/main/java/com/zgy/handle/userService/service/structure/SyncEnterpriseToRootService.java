package com.zgy.handle.userService.service.structure;

import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.userService.client.SecondRootFeignClient;
import com.zgy.handle.userService.model.structure.Enterprise;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class SyncEnterpriseToRootService {
    @Autowired
    private SecondRootFeignClient secondRootFeignClient;
    @Autowired
    private EnterpriseService enterpriseService;

    public void syncEnterpriseInfo(Long enterpriseId){
        Optional<Enterprise> enterpriseOptional = enterpriseService.findById(enterpriseId);
        if (enterpriseOptional.isPresent()){
            String parentServer = secondRootFeignClient.getParentServer();
            log.info("获取到的根节点的企业信息为:" + parentServer);
            String url = "http://" + parentServer + "/syncRoot/enterprise";
            OkHttpClient okHttpClient = new OkHttpClient();
            String jsonObj = JSONObject.toJSONString(enterpriseOptional.get());
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObj);
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    log.error("链接失败," + e.getMessage());
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    log.info("上报成功:" + response.body().string());
                }
            });

        }
    }

}
