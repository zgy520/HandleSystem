package com.zgy.handle.userService.service.base;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.model.common.UniqueInfo;

/**
 * 更新类的基类
 */
public interface UpdateService<T,U> extends BaseService<T> {
    /**
     * 唯一性判定
     * @param u
     * @param t
     * @return
     */
    UniqueInfo checkUnique(U u, T t);

    /**
     * 更新操作
     * @param u
     * @param t
     * @return
     */
    ResponseCode<T> update(U u, T t);

    /**
     * 更新之前进行的操作
     * @param u
     * @param t
     */
    T beforeUpdate(U u,T t);

    /**
     * 更新之后进行的操作
     * @param t
     * @param u
     */
    void postUpdate(T t,U u);

    /**
     * 删除操作
     * @param id
     * @return
     */
    ResponseCode<T> delete(Long id);
}
