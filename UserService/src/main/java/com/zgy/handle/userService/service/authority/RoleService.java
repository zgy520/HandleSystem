package com.zgy.handle.userService.service.authority;

import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.RoleDTO;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.authority.RoleRepository;
import com.zgy.handle.userService.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class RoleService extends SystemService {
    private RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findOne(Long id){
        Role role = new Role();
        role.setId(id);
        Example<Role> example = Example.of(role);
        return roleRepository.findOne(example);
    }

    /**
     * 根据角色id列表获取所对应的角色信息
     * @param roleIdList
     * @return
     */
    public Set<Role> findByRoleIdIn(List<Long> roleIdList){
        return roleRepository.findAllByIdIn(roleIdList);
    }

    public Page<Role> findAllByExample(Role roleQuery, Pageable pageable){
        ExampleMatcher matcher = ExampleMatcher
                    .matchingAll()
                    .withMatcher("name",contains())
                    .withMatcher("code",contains());
        /*Role roleExample = Role.builder()
                .code(roleQuery.getCode())
                .name(roleQuery.getName())
                .build();
        return roleRepository.findAll(Example.of(roleExample,matcher),pageable);*/
        return null;
    }

    @Transactional(readOnly = true)
    public void fetchAccountByRole(List<RoleDTO> roleDTOList){
        roleDTOList.stream().forEach(role -> {
            role.setUserList(roleRepository.findById(Long.valueOf(role.getId()))
            .get().getAccountSet().stream().map(Account::getId).map(String::valueOf).collect(Collectors.toList()));
        });
    }

}
