package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.structure.DepartmentAccount;
import com.zgy.handle.userService.repository.structure.DepartmentAccountRepository;
import com.zgy.handle.userService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentAccountService extends SystemService<DepartmentAccount> {
    private DepartmentAccountRepository departmentAccountRepository;
    public DepartmentAccountService(DepartmentAccountRepository departmentAccountRepository) {
        super(departmentAccountRepository);
        this.departmentAccountRepository = departmentAccountRepository;
    }
}
