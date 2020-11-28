package com.zgy.handle.userservice.service.user.update;

import com.zgy.handle.common.service.base.UpdateService;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.update.AccountUpdateVo;

public interface AccountUpdateService extends UpdateService<Account, AccountUpdateVo> {
    void updateAccountWithDepart(Long userId, Long departId);
}
