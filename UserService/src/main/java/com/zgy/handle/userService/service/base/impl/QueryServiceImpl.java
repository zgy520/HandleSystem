package com.zgy.handle.userService.service.base.impl;

import com.zgy.handle.userService.repository.base.QueryRepository;
import com.zgy.handle.userService.service.base.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class QueryServiceImpl<T,U> extends BaseServiceImpl<T> implements QueryService<T,U> {

    private final QueryRepository queryRepository;
    @Autowired
    public QueryServiceImpl(QueryRepository queryRepository) {
        super(queryRepository);
        this.queryRepository = queryRepository;
    }

    @Override
    public List<T> findAll() {
        return queryRepository.findAll();
    }

    @Override
    public Optional<T> findById(Long id) {
        return queryRepository.findById(id);
    }

    @Override
    public Object getOne(Long id) {
        return queryRepository.getOne(id);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return queryRepository.findAll(pageable);
    }
}
