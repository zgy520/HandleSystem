package com.zgy.handle.userService.repository.structure;

import com.zgy.handle.userService.model.authority.DepartmentPost;
import com.zgy.handle.userService.model.structure.DepartmentAccount;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentPostRepository extends SystemRepository<DepartmentPost> {
    List<DepartmentPost> findByPostId(Long postId);
    List<DepartmentPost> findByDepartmentId(Long departmentId);
    @Transactional
    @Modifying
    int deleteByPostId(Long DepartmentPost);

    @Transactional
    @Modifying
    int deleteByDepartmentId(Long departmentId);


}
