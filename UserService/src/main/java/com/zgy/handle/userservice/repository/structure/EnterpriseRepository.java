package com.zgy.handle.userservice.repository.structure;

import com.zgy.handle.userservice.model.structure.Enterprise;
import com.zgy.handle.userservice.repository.SystemRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EnterpriseRepository extends SystemRepository<Enterprise>, JpaSpecificationExecutor<Enterprise> {
    Set<Enterprise> findByIdIn(List<Long> idList);

    /**
     * 获取所有的父级id为空的企业列表
     * @return
     */
    List<Enterprise> findByParentIdIsNull();

    /**
     * 根据企业id获取所有的子企业
     * @param parentId
     * @return
     */
    List<Enterprise> findByParentId(Long parentId);
}
