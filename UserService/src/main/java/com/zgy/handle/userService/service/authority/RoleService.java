package com.zgy.handle.userService.service.authority;

import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.RoleDTO;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.authority.RoleRepository;
import com.zgy.handle.userService.service.SystemService;
import com.zgy.handle.userService.service.user.AccountService;
import com.zgy.handle.userService.util.Str.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleService extends SystemService<Role, RoleDTO> {
    private RoleRepository roleRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<Role> findByDynamicQuery(Pageable pageable, RoleDTO dto) {
        Specification<Role> specification = Specification
                .where(dto.getName() == null? null : RoleRepository.nameContains(dto.getName()))
                .and(dto.getCode() == null? null : RoleRepository.codeContains(dto.getCode()))
                .and(dto.getNote() == null? null : RoleRepository.noteContains(dto.getNote()));
        return roleRepository.findAll(specification,pageable);
    }

    /**
     * 根据角色id列表获取所对应的角色信息
     * @param roleIdList
     * @return
     */
    public Set<Role> findByRoleIdIn(List<Long> roleIdList){
        return roleRepository.findAllByIdIn(roleIdList);
    }

    @Transactional(readOnly = true)
    public void fetchAccountByRole(List<RoleDTO> roleDTOList){
        roleDTOList.stream().forEach(role -> {
            role.setUserList(roleRepository.findById(Long.valueOf(role.getId()))
                    .get().getAccountSet().stream().map(Account::getId).map(String::valueOf).collect(Collectors.toList()));
        });
    }

    @Override
    public void beforeUpdate(RoleDTO roleDTO, Role role) {
        List<Long> userIdList = StrUtils.transformList(roleDTO.getUserList(),Long::parseLong);
        Set<Account> accountSet = accountService.findByIdIn(userIdList);
        role.setAccountSet(accountSet);
    }
}
