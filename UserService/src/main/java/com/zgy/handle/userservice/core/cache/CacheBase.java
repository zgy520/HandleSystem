package com.zgy.handle.userservice.core.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.Cache;
import org.ehcache.CacheManager;

import java.lang.reflect.ParameterizedType;

/**
 * 缓存基础类
 *
 * @param <K>
 * @param <V>
 */
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

        cacheManager = CacheManagerFactory.getCacheManager(this.cacheName, kClass, vClass);
        preDataConfig = cacheManager.getCache(this.cacheName, kClass, vClass);


    }

    private final  CacheManager cacheManager;
    protected final Cache<K,V> preDataConfig;


    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 添加数据到缓存
     *
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
    public Cache getCache() {
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
