package com.zgy.handle.userService.service.authority;

import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.repository.authority.RoleRepository;
import com.zgy.handle.userService.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
public class RoleService extends SystemService {
    private RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    public Page<Role> findAllByExample(Role roleQuery, Pageable pageable){
        ExampleMatcher matcher = ExampleMatcher
                    .matchingAll()
                    .withMatcher("name",contains())
                    .withMatcher("code",contains());
        Role roleExample = Role.builder()
                .code(roleQuery.getCode())
                .name(roleQuery.getName())
                .build();
        return roleRepository.findAll(Example.of(roleExample,matcher),pageable);
    }
}
