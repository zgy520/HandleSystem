package com.zgy.handle.userService.service.authority.role.update;

import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.model.authority.role.RoleDTO;
import com.zgy.handle.userService.model.common.UniqueInfo;
import com.zgy.handle.userService.repository.authority.role.RoleQueryRepository;
import com.zgy.handle.userService.repository.authority.role.RoleUpdateRepository;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RoleUpdateServiceImpl extends UpdateServiceImpl<Role, RoleDTO> implements RoleUpdateService {
    private RoleUpdateRepository roleUpdateRepository;
    @Autowired
    private RoleQueryRepository roleQueryRepository;
    @Autowired
    public RoleUpdateServiceImpl(RoleUpdateRepository roleUpdateRepository) {
        super(roleUpdateRepository);
        this.roleUpdateRepository = roleUpdateRepository;
    }

    @Override
    public void beforeUpdate(RoleDTO roleDTO, Role role) {
        if (StringUtils.isNotBlank(roleDTO.getId())){
            Optional<Role> oldRoleOptional = roleQueryRepository.findById(Long.valueOf(roleDTO.getId()));
            role.setAccountSet(oldRoleOptional.get().getAccountSet());
        }

    }

    @Override
    public UniqueInfo checkUnique(RoleDTO roleDTO, Role role) {
        Long count = StringUtils.isBlank(roleDTO.getId())? roleQueryRepository.countByCodeOrName(roleDTO.getCode(),roleDTO.getName()) : roleQueryRepository.countByCodeAndIdNot(roleDTO.getCode(),Long.valueOf(roleDTO.getId()));
        if (count > 0){
            return UniqueInfo.getUniqueInfo("角色代码或名称重复");
        }
        return super.checkUnique(roleDTO, role);
    }
}
