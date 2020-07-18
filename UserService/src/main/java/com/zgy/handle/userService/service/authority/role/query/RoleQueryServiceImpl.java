package com.zgy.handle.userService.service.authority.role.query;

import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.model.authority.role.RoleDTO;
import com.zgy.handle.userService.model.authority.role.Role_;
import com.zgy.handle.userService.repository.authority.role.RoleQueryRepository;
import com.zgy.handle.userService.repository.base.QueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleQueryServiceImpl extends QueryServiceImpl<Role, RoleDTO> implements RoleQueryService {
    private RoleQueryRepository roleQueryRepository;
    @Autowired
    public RoleQueryServiceImpl(RoleQueryRepository roleQueryRepository){
        super(roleQueryRepository);
        this.roleQueryRepository = roleQueryRepository;
    }
    public RoleQueryServiceImpl(QueryRepository queryRepository) {
        super(queryRepository);
    }

    @Override
    public Specification<Role> querySpecification(Pageable pageable, RoleDTO dto) {
        Specification<Role> roleSpecification = Specification.where(StringUtils.isNotBlank(dto.getCode())?roleQueryRepository.fieldLike(Role_.CODE,dto.getCode()) : null)
                .and(StringUtils.isNotBlank(dto.getName())?roleQueryRepository.fieldLike(Role_.NAME,dto.getName()) : null)
                .and(StringUtils.isNotBlank(dto.getNote())?roleQueryRepository.fieldLike(Role_.NOTE,dto.getNote()):null);
        return roleSpecification;
    }
}
