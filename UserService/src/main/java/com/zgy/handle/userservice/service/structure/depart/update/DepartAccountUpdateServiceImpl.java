package com.zgy.handle.userservice.service.structure.depart.update;

import com.zgy.handle.common.repository.base.UpdateRepository;
import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.userservice.model.structure.DepartmentAccount;
import com.zgy.handle.userservice.repository.structure.depart.DepartAccountUpdateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2020/10/5 8:46
 */
@Service
@Slf4j
public class DepartAccountUpdateServiceImpl extends BaseUpdateServiceImpl<DepartmentAccount,DepartmentAccount> implements DepartAccountUpdateService {
    private DepartAccountUpdateRepository departAccountUpdateRepository;
    public DepartAccountUpdateServiceImpl(DepartAccountUpdateRepository departAccountUpdateRepository) {
        super(departAccountUpdateRepository);
        this.departAccountUpdateRepository = departAccountUpdateRepository;
    }
}
