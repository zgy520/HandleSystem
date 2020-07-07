package com.zgy.handle.userService.service.user.query;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.cross.RolePostDTO;
import com.zgy.handle.userService.model.user.query.AccountQueryVo;
import com.zgy.handle.userService.service.base.QueryService;

public interface AccountQueryService extends QueryService<Account, AccountQueryVo> {
    /**
     * 根据用户id获取对应的角色和岗位信息
     * @param userId
     * @return
     */
    RolePostDTO fetchRolePostName(Long userId);

    Account getAccountWithRole(Long accountId);
}
