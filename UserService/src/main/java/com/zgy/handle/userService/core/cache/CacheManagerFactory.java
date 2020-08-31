package com.zgy.handle.userService.core.cache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * CacheManager构造工厂
 */
public class CacheManagerFactory {
    /**
     * 构造cacheManager
     * @param name
     * @param keyObj
     * @param valueObj
     * @return
     */
    public static CacheManager getCacheManager(String name,Class keyObj,Class valueObj){
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache(name,
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(keyObj,valueObj, ResourcePoolsBuilder.heap(100))
                                .build())
                .build(true);
        return cacheManager;
    }
}
