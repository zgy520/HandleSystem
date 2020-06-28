package com.zgy.handle.userService.repository.user;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.Post_;
import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.Role_;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentAccount;
import com.zgy.handle.userService.model.structure.Department_;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.Account_;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

@Repository
public interface AccountRepository extends SystemRepository<Account> {
    Account findByLoginName(String loginName);

    /**
     * 根据id列表获取所有的账户信息
     * @param idList
     * @return
     */
    Set<Account> findAllByIdIn(List<Long> idList);

    static Specification<Account> nameContains(String name){
        return fieldContains(Account_.NAME,name);
    }

    static Specification<Account> fieldContains(String filed,String value){
        return (root,query,builder) -> builder.like(root.get(filed),contains(value));
    }

    /**
     * 根据部门进行模糊查询
     * @param departName
     * @return
     */
    static Specification<Account> departNameLike(String departName){
        return new Specification<Account>(){
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Account, DepartmentAccount> join = root.join("departmentAccounts");
                Join<Department,Account> departJoin = join.join("department");
                return criteriaBuilder.like(departJoin.get(Department_.NAME),"%" + departName + "%");
            }
        };
    }

    /**
     * 根据角色进行查询
     * @param roleName
     * @return
     */
    static Specification<Account> roleLike(String roleName){
        return new Specification<Account>(){
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Account, Role> roleJoin = root.join("roleSet");
                return criteriaBuilder.like(roleJoin.get(Role_.name), "%" + roleName + "%");
            }
        };
    }

    /**
     * 根据岗位进行查询
     * @param postName
     * @return
     */
    static Specification<Account> postLike(String postName){
        return new Specification<Account>(){
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Account, Post> postJoin = root.join("postSet");
                return criteriaBuilder.like(postJoin.get(Post_.name),"%" + postName + "%");
            }
        };
    }

    private static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
