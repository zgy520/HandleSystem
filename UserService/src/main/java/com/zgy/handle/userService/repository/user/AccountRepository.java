package com.zgy.handle.userService.repository.user;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

@Repository
public interface AccountRepository extends SystemRepository<Account>, JpaSpecificationExecutor<Account> {
    Account findByLoginName(String loginName);

    static Specification<Account> nameContains(String name){
        return fieldContains("name",name);
    }

    static Specification<Account> fieldContains(String filed,String value){
        return (root,query,builder) -> builder.like(root.get(filed),contains(value));
    }

    private static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
