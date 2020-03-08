package com.zgy.handle.userService.repository.authority;

import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends SystemRepository<Role>, JpaSpecificationExecutor<Role> {

    Set<Role> findAllByIdIn(List<Long> roleIdList);

     static Specification<Role> nameContains(String name){
        return (root,query,builder) -> builder.like(root.get("name"),contains(name));
    }
     static Specification<Role> codeContains(String code){
        return (root,query,builder) -> builder.like(root.get("code"),contains(code));
    }
    static Specification<Role> noteContains(String note){
        return (root,query,builder) -> builder.like(root.get("note"),contains(note));
    }

    private static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
