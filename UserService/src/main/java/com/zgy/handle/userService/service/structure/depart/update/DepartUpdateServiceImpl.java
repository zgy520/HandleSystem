package com.zgy.handle.userService.service.structure.depart.update;

import com.zgy.handle.userService.model.authority.depart.DepartUpdateDTO;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.repository.structure.depart.DepartUpdateRepository;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartUpdateServiceImpl extends UpdateServiceImpl<Department, DepartUpdateDTO> implements DepartUpdateService {
    private DepartUpdateRepository departUpdateRepository;
    @Autowired
    public DepartUpdateServiceImpl(DepartUpdateRepository departUpdateRepository) {
        super(departUpdateRepository);
        this.departUpdateRepository = departUpdateRepository;
    }
}
