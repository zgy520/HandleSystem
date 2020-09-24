package com.zgy.handle.userservice.core.cache;

import com.zgy.handle.userservice.model.parameter.Dict;
import com.zgy.handle.userservice.service.param.dict.DictServiceBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a4423
 */
@Component
@Slf4j
public class DictCache extends CacheBase<String, List> {
    private static final String CACHE_NAME = "dictCache";
    @Autowired
    private DictServiceBase dictService;


    public DictCache() {
        super(CACHE_NAME);
    }


    @Override
    @PostConstruct
    public void initData() {
        List<Dict> dictList = dictService.findAll();
        List<Dict> rootList = dictList.stream().filter(dict -> dict.getParent() == null).collect(Collectors.toList());
        for (Dict dict : rootList) {
            List<Dict> childrenList = dictList.stream().filter(dict1 -> dict1.getParent() != null && dict1.getParent().getId().equals(dict.getId())).collect(Collectors.toList());
            /*getCache().put(dict.getCode(), childrenList);*/
            addToCache(dict.getCode(),childrenList);
        }
    }
}
