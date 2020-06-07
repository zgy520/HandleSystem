package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.client.SecondRootFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SyncEnterpriseToRootService {
    @Autowired
    private SecondRootFeignClient secondRootFeignClient;

    public void syncEnterpriseInfo(){
        String parentServer = secondRootFeignClient.getParentServer();
        log.info("获取到的根节点的企业信息为:" + parentServer);
    }

}
