package com.zgy.handle.userService.core.cache;

import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.service.param.param.query.ParamQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class ParamCache extends CacheBase<String, String> {
    private final static String cacheName = "paramCache";
    @Autowired
    private ParamQueryService paramQueryService;

    public ParamCache() {
        super(cacheName);
    }

    @Override
    @PostConstruct
    public void initData() {
        List<Param> paramList = paramQueryService.findAll();
        paramList.forEach(param -> {
            preDataConfig.put(param.getCode(), param.getValue());
        });
        log.info("参数数据初始化完毕");
    }





}
