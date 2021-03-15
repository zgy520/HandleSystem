package com.zgy.handle.userservice.controller.user.update;

import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.core.error.ErrorNum;
import com.zgy.handle.userservice.core.exception.BusinessException;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.update.AccountUpdateVo;
import com.zgy.handle.userservice.service.user.query.AccountQueryService;
import com.zgy.handle.userservice.service.user.update.AccountUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author a4423
 */
@RestController
@RequestMapping(value = "account/update")
@Slf4j
public class AccountUpdateRefactoryController extends BaseUpdateController<Account, AccountUpdateVo> {
    @Autowired
    private AccountUpdateMapper accountUpdateMapper;
    private final AccountQueryService accountQueryService;
    private final AccountUpdateService accountUpdateService;

    public AccountUpdateRefactoryController(AccountUpdateService accountUpdateService, AccountQueryService accountQueryService) {
        super(accountUpdateService, accountQueryService);
        this.accountQueryService = accountQueryService;
        this.accountUpdateService = accountUpdateService;

    }

    @Override
    public Account convertUtoT(AccountUpdateVo accountUpdateVo) {
        return accountUpdateMapper.toAccount(accountUpdateVo);
    }

    @PostMapping(value = "updateAccountWithDepart")
    public ResponseCode<String> updateAccountWithDepart(Long userId, Long departId) {
        accountUpdateService.updateAccountWithDepart(userId, departId);
        return ResponseCode.sucess();
    }

    @DeleteMapping(value = "deleteList/{idList}")
    public ResponseCode<String> deleteList(@PathVariable(value = "idList") String idList){
        ResponseCode<String> responseCode = ResponseCode.sucess();
        String[] ids = idList.split(",");
        if (ids.length == 2){
//            throw new RuntimeException("x");
            throw new BusinessException(ErrorNum.ERROR_NOT_FOUND_DATA);
        }
        for (String id : ids){
            accountUpdateService.delete(Long.valueOf(id));
        }
        return responseCode;
    }

}
