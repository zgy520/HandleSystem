package com.zgy.handle.userService.service.base.impl;

import com.zgy.handle.userService.repository.base.BaseRepository;
import com.zgy.handle.userService.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    private final BaseRepository baseRepository;
    @Autowired
    public BaseServiceImpl(BaseRepository baseRepository){
        this.baseRepository = baseRepository;
    }
}
