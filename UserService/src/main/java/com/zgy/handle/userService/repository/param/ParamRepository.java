package com.zgy.handle.userService.repository.param;

import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamType;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.AccountType;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

@Repository
public interface ParamRepository extends SystemRepository<Param>, JpaSpecificationExecutor<Param> {
    /**
     * 根据code和type获取参数列表
     * @param code
     * @param paramType
     * @return
     */
    Param findAllByCodeAndParamType(String code, ParamType paramType);

    List<Param> findByParentId(Long parentId);

    /**
     * 分页版本
     * @param code
     * @param paramType
     * @param pageable
     * @return
     */
    Page<Param> findAllByCodeAndParamType(String code, ParamType paramType, Pageable pageable);


    static Specification<Param> typeFilter(ParamType paramType){
        return (root,query,builder) -> builder.equal(root.get("paramType"),paramType);
    }

    /*static Specification<Param> codeContains(String code){
        return (root,query,builder) -> builder.like(root.get("code"),contains(code));
    }
    static Specification<Param> noteContains(String note){
        return (root,query,builder) -> builder.like(root.get("note"),contains(note));
    }



    private static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }*/
}
