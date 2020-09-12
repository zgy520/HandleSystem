package com.zgy.handle.userservice.service.authority.role;

import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.RoleDTO;
import com.zgy.handle.userservice.repository.authority.RoleRepository;
import com.zgy.handle.userservice.service.BaseSystemService;
import com.zgy.handle.userservice.service.user.AccountServiceBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RoleServiceBase extends BaseSystemService<Role, RoleDTO> {
    private RoleRepository roleRepository;
    @Autowired
    private AccountServiceBase accountService;
    @Autowired
    public RoleServiceBase(RoleRepository roleRepository) {
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
        /*roleDTOList.stream().forEach(role -> {
            role.setUserList(roleRepository.findById(Long.valueOf(role.getId()))
                    .get().getAccountSet().stream().map(Account::getId).map(String::valueOf).collect(Collectors.toList()));
        });*/
    }

    @Override
    public void beforeUpdate(RoleDTO roleDTO, Role role) {
        /*List<Long> userIdList = StrUtils.transformList(roleDTO.getUserList(),Long::parseLong);
        Set<Account> accountSet = accountService.findByIdIn(userIdList);
        role.setAccountSet(accountSet);*/
    }
}
