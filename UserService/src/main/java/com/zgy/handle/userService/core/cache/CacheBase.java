package com.zgy.handle.userService.core.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.lang.reflect.ParameterizedType;

@Slf4j
public abstract class CacheBase<K, V> {
    private String cacheName;
    private Class<K> kClass;
    private Class<V> vClass;

    public CacheBase(String cacheName) {
        this.cacheName = cacheName;
        kClass = (Class<K>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
        vClass = (Class<V>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[1];

        cacheManager = CacheManagerFactory.getCacheManager(cacheName, kClass, vClass);
        preDataConfig = cacheManager.createCache(cacheName, CacheConfigurationBuilder.newCacheConfigurationBuilder(kClass, vClass,
                ResourcePoolsBuilder.heap(100)).build());


    }

    private static volatile CacheManager cacheManager;
    protected static volatile Cache preDataConfig;


    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 添加数据到缓存
     * @param k
     * @param v
     */
    public void addToCache(K k, V v) {
        preDataConfig.put(k, v);
    }

    /**
     * 根据k获取数据
     *
     * @param k
     * @return
     */
    public V getValueByKey(K k) {
        return (V) preDataConfig.get(k);
    }

    /**
     * 获取缓存对象
     *
     * @return
     */
    public static Cache getCache() {
        return preDataConfig;
    }

    /**
     * 清空缓存
     */
    public void clearCache() {
        preDataConfig.clear();
    }

    public void close() {
        cacheManager.close();
    }
}
