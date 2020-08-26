package com.zgy.handle.userService.core.cache;

import com.zgy.handle.userService.model.parameter.Dict;
import com.zgy.handle.userService.service.param.dict.DictService;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DictCache {
    @Autowired
    private DictService dictService;

    private static volatile CacheManager cacheManager;
    private static volatile Cache<String, List> preDictConfig;

     {
        cacheManager = CacheManagerFactory.getCacheManager("preDict",String.class, List.class);
        preDictConfig = cacheManager.createCache("preDict",CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, List.class,
                ResourcePoolsBuilder.heap(100)).build());
    }


    @PostConstruct
    private void init(){
        List<Dict> dictList = dictService.findAll();
        List<Dict> rootList = dictList.stream().filter(dict -> dict.getParent() == null).collect(Collectors.toList());
        for (Dict dict : rootList){
            List<Dict> childrenList = dictList.stream().filter(dict1 -> dict1.getParent() != null && dict1.getParent().getId().equals(dict.getId())).collect(Collectors.toList());
            preDictConfig.put(dict.getCode(),childrenList);
        }
    }

    public static List<Dict> getDictList(String key){
        return preDictConfig.get(key);
    }

    public static Cache<String, List> getPreDictCache(){
        return preDictConfig;
    }
}
