package com.zgy.handle.userService.service.structure.depart.query;

import com.zgy.handle.userService.model.authority.depart.DepartQueryDTO;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentDTO;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.repository.structure.depart.DepartQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import com.zgy.handle.userService.util.tree.TreeConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartQueryServiceImpl extends QueryServiceImpl<Department, DepartQueryDTO> implements DepartQueryService {

    private DepartQueryRepository departQueryRepository;
    @Autowired
    public DepartQueryServiceImpl(DepartQueryRepository departQueryRepository) {
        super(departQueryRepository);
    }

    @Override
    public List<DepartQueryDTO> getDepartmentDtoList(List<DepartQueryDTO> departQueryDTOList){
        departQueryDTOList.stream().forEach(departmentDTO -> {
            Enterprise enterprise = this.fetchIndustry(Long.valueOf(departmentDTO.getId()));
            if (enterprise != null){
                departmentDTO.setEnterpriseName(enterprise.getName());
                departmentDTO.setEnterpriseId(enterprise.getId().toString());
            }
        });
        TreeConvert treeUtils = new TreeConvert(departQueryDTOList);
        try {
            List<DepartQueryDTO> departmentDTOS = treeUtils.toJsonArray(DepartQueryDTO.class);
            return departmentDTOS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Specification<Department> querySpecification(Pageable pageable, DepartQueryDTO dto) {
        return null;
    }


    /**
     * 根据部门id获取部门对应的企业信息
     * @param departId
     * @return
     */
    @Transactional(readOnly = true)
    public Enterprise fetchIndustry(Long departId){
        Enterprise enterprise = null;
        Optional<Department> departmentOptional = this.findById(departId);
        if (departmentOptional.isPresent()){
            enterprise = departmentOptional.get().getEnterprise();
        }
        return enterprise;
    }
}
