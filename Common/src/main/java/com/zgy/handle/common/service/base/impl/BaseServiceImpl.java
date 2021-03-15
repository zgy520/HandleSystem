package com.zgy.handle.common.service.base.impl;

import com.zgy.handle.common.repository.base.BaseRepository;
import com.zgy.handle.common.service.RequestUserService;
import com.zgy.handle.common.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
public abstract class BaseServiceImpl<T> extends RequestUserService implements BaseService<T> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected final BaseRepository baseRepository;
    @Autowired
    public BaseServiceImpl(BaseRepository baseRepository){
        this.baseRepository = baseRepository;
    }
}
