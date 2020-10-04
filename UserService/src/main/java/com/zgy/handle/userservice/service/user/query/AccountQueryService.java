package com.zgy.handle.userservice.service.user.query;

import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.cross.RolePostDTO;
import com.zgy.handle.userservice.model.user.query.AccountQueryVo;

public interface AccountQueryService extends QueryService<Account, AccountQueryVo> {
    /**
     * 根据用户id获取对应的角色和岗位信息
     *
     * @param userId
     * @return
     */
    RolePostDTO fetchRolePostName(Long userId);

    Account getAccountWithRole(Long accountId);
}
