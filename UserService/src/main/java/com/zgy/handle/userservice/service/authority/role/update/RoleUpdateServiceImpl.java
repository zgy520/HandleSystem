package com.zgy.handle.userservice.service.authority.role.update;

import com.zgy.handle.common.model.common.UniqueInfo;
import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.RoleDTO;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.repository.authority.role.RoleQueryRepository;
import com.zgy.handle.userservice.repository.authority.role.RoleUpdateRepository;
import com.zgy.handle.userservice.repository.user.query.AccountQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleUpdateServiceImpl extends BaseUpdateServiceImpl<Role, RoleDTO> implements RoleUpdateService {
    private RoleUpdateRepository roleUpdateRepository;
    @Autowired
    private RoleQueryRepository roleQueryRepository;
    @Autowired
    private AccountQueryRepository accountQueryRepository;

    @Autowired
    public RoleUpdateServiceImpl(RoleUpdateRepository roleUpdateRepository) {
        super(roleUpdateRepository);
        this.roleUpdateRepository = roleUpdateRepository;
    }

    @Override
    public void fillRelateObj(RoleDTO roleDTO, Role role) {
        if (StringUtils.isNotBlank(roleDTO.getId())) {
            Optional<Role> oldRoleOptional = roleQueryRepository.findById(Long.valueOf(roleDTO.getId()));
            role.setAccountSet(oldRoleOptional.get().getAccountSet());
        }
    }

    @Override
    public UniqueInfo checkUnique(RoleDTO roleDTO, Role role) {
        Long count = StringUtils.isBlank(roleDTO.getId()) ? roleQueryRepository.countByCodeOrName(roleDTO.getCode(), roleDTO.getName()) : roleQueryRepository.countByCodeAndIdNot(roleDTO.getCode(), Long.valueOf(roleDTO.getId()));
        if (count > 0) {
            return UniqueInfo.getUniqueInfo("角色代码或名称重复");
        }
        return super.checkUnique(roleDTO, role);
    }

    @Override
    public String relateUser(Long roleId, String selectedUserList) {
        String result = "成功";
        Optional<Role> roleOptional = roleQueryRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            List<Long> userIdList = Arrays.asList(selectedUserList.split(",")).stream().map(Long::valueOf).collect(Collectors.toList());
            Set<Account> accountList = accountQueryRepository.findByIdIn(userIdList).stream().collect(Collectors.toSet());
            role.setAccountSet(accountList);
            roleUpdateRepository.save(role);
            return result;
        } else {
            throw new EntityNotFoundException("不存在ID为：" + roleId.toString() + "的角色信息");
        }
    }
}
