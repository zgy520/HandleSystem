package com.zgy.handle.userservice.core.cache.tier;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * @author: a4423
 * @date: 2020/9/19 16:27
 */
@Component
@Slf4j
public class ThreeTierCache {
    @PostConstruct
    public void initThreeTier() {
        log.info("开始进行分层初始化");
        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File("e:\\", "mydata")))
                .withCache("threeTieredCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10, EntryUnit.ENTRIES)
                                .offheap(1, MemoryUnit.MB)
                                .disk(20, MemoryUnit.MB, true)))
                .build(true);
        Cache<Long,String> threeTieredCache = persistentCacheManager.getCache("threeTieredCache",Long.class,String.class);
        threeTieredCache.put(1L,"stilAvaliableAfterRestart!");
        threeTieredCache.put(2L,"xwlkdfd");

//        persistentCacheManager.close();
        log.info("分层初始化完毕:" + threeTieredCache.get(2L));
    }
}
