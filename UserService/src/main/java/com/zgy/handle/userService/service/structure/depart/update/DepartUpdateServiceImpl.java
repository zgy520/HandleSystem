package com.zgy.handle.userService.service.structure.depart.update;

import com.zgy.handle.userService.model.authority.depart.DepartUpdateDTO;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.repository.structure.depart.DepartUpdateRepository;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
import com.zgy.handle.userService.service.structure.depart.query.DepartQueryService;
import com.zgy.handle.userService.service.structure.enterprise.query.EnterpriseQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DepartUpdateServiceImpl extends UpdateServiceImpl<Department, DepartUpdateDTO> implements DepartUpdateService {
    @Autowired
    private DepartQueryService departQueryService;
    @Autowired
    private EnterpriseQueryService enterpriseQueryService;
    private DepartUpdateRepository departUpdateRepository;
    @Autowired
    public DepartUpdateServiceImpl(DepartUpdateRepository departUpdateRepository) {
        super(departUpdateRepository);
        this.departUpdateRepository = departUpdateRepository;
    }

    @Override
    public void beforeUpdate(DepartUpdateDTO departUpdateDTO, Department department) {
        if (StringUtils.isNotBlank(departUpdateDTO.getParentId())) {
            Optional<Department> superDepartmentOptional = departQueryService.findById(Long.valueOf(departUpdateDTO.getParentId()));
            if (superDepartmentOptional.isPresent()){
                department.setParent(superDepartmentOptional.get());
            }
        }
        if (StringUtils.isNotBlank(departUpdateDTO.getEnterpriseId())) {
            Optional<Enterprise> enterpriseOptional = enterpriseQueryService.findById(Long.valueOf(departUpdateDTO.getEnterpriseId()));
            department.setEnterprise(enterpriseOptional.get());
        }
    }
}
