package com.zgy.handle.userService.core.cache;

import com.zgy.handle.userService.model.parameter.Dict;
import com.zgy.handle.userService.service.param.dict.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DictCache extends CacheBase<String,List>{
    private static final String cacheName = "dictCache";
    @Autowired
    private DictService dictService;


    public DictCache() {
        super(cacheName);
    }


    @Override
    @PostConstruct
    public void initData() {
        List<Dict> dictList = dictService.findAll();
        List<Dict> rootList = dictList.stream().filter(dict -> dict.getParent() == null).collect(Collectors.toList());
        for (Dict dict : rootList){
            List<Dict> childrenList = dictList.stream().filter(dict1 -> dict1.getParent() != null && dict1.getParent().getId().equals(dict.getId())).collect(Collectors.toList());
            getCache().put(dict.getCode(),childrenList);
        }
    }
}
