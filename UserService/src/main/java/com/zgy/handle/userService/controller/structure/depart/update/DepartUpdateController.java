package com.zgy.handle.userService.controller.structure.depart.update;

import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.model.authority.depart.DepartUpdateDTO;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.service.structure.depart.query.DepartQueryService;
import com.zgy.handle.userService.service.structure.depart.update.DepartUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "depart/update")
@Slf4j
public class DepartUpdateController extends UpdateController<Department, DepartUpdateDTO> {
    @Autowired
    private DepartUpdateMapper departUpdateMapper;
    private DepartUpdateService departUpdateService;
    private DepartQueryService departQueryService;
    @Autowired
    public DepartUpdateController(DepartUpdateService departUpdateService, DepartQueryService departQueryService) {
        super(departUpdateService, departQueryService);
        this.departQueryService = departQueryService;
        this.departUpdateService = departUpdateService;
    }

    @Override
    public Department convertUtoT(DepartUpdateDTO departUpdateDTO) {
        return departUpdateMapper.toDepartment(departUpdateDTO);
    }
}
