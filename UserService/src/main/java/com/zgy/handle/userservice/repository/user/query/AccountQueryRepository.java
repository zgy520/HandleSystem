package com.zgy.handle.userservice.repository.user.query;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.Post_;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.Role_;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.structure.DepartmentAccount;
import com.zgy.handle.userservice.model.structure.Department_;
import com.zgy.handle.userservice.model.user.Account;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author a4423
 */
@Repository
public interface AccountQueryRepository extends QueryRepository<Account> {
    /**
     * 根据登录名称获取账号
     *
     * @param loginName
     * @return
     */
    Account findByLoginName(String loginName);


    /**
     * 根据登录名称获取对应的数量
     *
     * @param loginName
     * @return
     */
    Long countByLoginName(String loginName);

    /**
     * 获取特定id之外的对于登录名称的数量
     *
     * @param loginName
     * @param id
     * @return
     */
    Long countByLoginNameAndIdNot(String loginName, Long id);

    List<Account> findByIdIn(List<Long> accountIdList);

    /*@Query(value = "select account from Account account JOIN FETCH account.roleSet")
    Account findByUserIdWithRole(Long accountId);*/

    /**
     * 根据部门名称进行模糊查询
     * @param departName
     * @return
     */
    static Specification<Account> departNameLike(String departName) {
        return new Specification<Account>() {

            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Account, DepartmentAccount> join = root.join("departmentAccounts");
                Join<Department, Account> departJoin = join.join("department");
                return criteriaBuilder.like(departJoin.get(Department_.NAME), "%" + departName + "%");
            }
        };
    }

    /**
     * 根据角色名称进行模糊查询
     * @param roleName
     * @return
     */
    static Specification<Account> roleNameLike(String roleName) {
        return new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Account, Role> join = root.join("roleSet");
                return criteriaBuilder.like(join.get(Role_.NAME), "%" + roleName + "%");
            }
        };
    }
    /**
     * 根据岗位名称进行模糊查询
     * @param postName
     * @return
     */
    static Specification<Account> postNameLike(String postName) {
        return new Specification<Account>() {

            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<Account, Post> join = root.join("postSet");
                return criteriaBuilder.like(join.get(Post_.NAME), "%" + postName + "%");
            }
        };
    }


}
