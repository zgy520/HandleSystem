package com.zgy.handle.userService.service.base.impl;

import com.zgy.handle.userService.model.BaseModel_;
import com.zgy.handle.userService.repository.base.QueryRepository;
import com.zgy.handle.userService.service.base.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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

    /**
     * 实现动态查询
     * @param pageable
     * @param dto
     * @return
     */
    @Override
    public Page<T> findByDynamicQuery(Pageable pageable, U dto) {
        // 单位权限
        Specification<T> permissionSpec = getPermissionQuery();
        Specification querySpec = permissionSpec.and(querySpecification(dto));
        return this.queryRepository.findAll(querySpec,pageable);
    }

    /**
     * 实现业务数据的查询
     * @param dto
     * @return
     */
    public abstract Specification<T> querySpecification(U dto);

    /**
     * 实现权限的查询
     * @return
     */
    @Override
    public Specification<T> getPermissionQuery() {
        // 获取个人的权限
        // 个人权限
        Specification<T> personalPermissionSpec = Specification
                .where(queryRepository.fieldEquals(BaseModel_.CREATED_ID,getPersonalId()));
        // 部门权限
        Specification<T> departPermissionSpec = Specification
                .where(queryRepository.fieldEquals(BaseModel_.BELONG_ID,getDepartId()));

        // 单位权限
        Specification<T> unitPermissionSpec = Specification
                .where(queryRepository.fieldIn(BaseModel_.BELONG_ID,"1"));
        return personalPermissionSpec;
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
