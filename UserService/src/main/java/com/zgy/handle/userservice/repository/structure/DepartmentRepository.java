package com.zgy.handle.userservice.repository.structure;

import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.repository.SystemRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DepartmentRepository extends SystemRepository<Department>, JpaSpecificationExecutor<Department> {
    /**
     * 根据id列表获取所有的实体对象列表
     * @param idList
     * @return
     */
    Set<Department> findByIdIn(List<Long> idList);

    /**
     * 获取所有的父级id为空的企业列表
     * @return
     */
    List<Department> findByParentIdIsNull();

    /**
     * 根据企业id获取所有的子企业
     * @param parentId
     * @return
     */
    List<Department> findByParentId(Long parentId);
}
