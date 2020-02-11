package com.zgy.handle.userService.service.authority;

import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.repository.authority.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public final class RoleSpecificationsService {
    @Autowired
    private RoleRepository roleRepository;
    public Page<Role> findAllByDynamicQuery(Role roleQuery, Pageable pageable){
        Specification<Role> specification = Specification
                .where(roleQuery.getName() == null? null : RoleRepository.nameContains(roleQuery.getName()))
                .and(roleQuery.getCode() == null? null : RoleRepository.codeContains(roleQuery.getCode()))
                .and(roleQuery.getNote() == null? null : RoleRepository.noteContains(roleQuery.getNote()));
        return roleRepository.findAll(specification,pageable);
    }
}
