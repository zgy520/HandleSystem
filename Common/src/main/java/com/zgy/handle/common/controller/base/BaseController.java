package com.zgy.handle.common.controller.base;

import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.common.service.base.UpdateService;
import lombok.extern.slf4j.Slf4j;

/**
 * 基础controller
 * @param <T>
 */
@Slf4j
public abstract class BaseController<T> {
    private final UpdateService updateService;
    private final QueryService queryService;
    public BaseController(QueryService queryService,UpdateService updateService) {
        this.updateService = updateService;
        this.queryService = queryService;
    }

}
