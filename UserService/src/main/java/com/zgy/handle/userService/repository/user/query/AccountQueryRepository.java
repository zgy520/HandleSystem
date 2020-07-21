package com.zgy.handle.userService.repository.user.query;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.base.QueryRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountQueryRepository extends QueryRepository<Account> {
    /**
     * 根据登录名称获取账号
     * @param loginName
     * @return
     */
    Account findByLoginName(String loginName);


    /**
     * 根据登录名称获取对应的数量
     * @param loginName
     * @return
     */
    Long countByLoginName(String loginName);

    /**
     * 获取特定id之外的对于登录名称的数量
     * @param loginName
     * @param id
     * @return
     */
    Long countByLoginNameAndIdNot(String loginName,Long id);

    List<Account> findByIdIn(List<Long> accountIdList);

    /*@Query(value = "select account from Account account JOIN FETCH account.roleSet")
    Account findByUserIdWithRole(Long accountId);*/
}
