package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentDTO;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.repository.structure.DepartmentRepository;
import com.zgy.handle.userService.service.SystemService;
import com.zgy.handle.userService.util.tree.TreeConvert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class DepartmentService extends SystemService<Department,DepartmentDTO> {
    private DepartmentRepository departmentRepository;
    @Autowired
    private EnterpriseService enterpriseService;
    public DepartmentService(DepartmentRepository departmentRepository) {
        super(departmentRepository);
        this.departmentRepository = departmentRepository;
    }

    /**
     * 根据id获取实体对象列表
     * @param idList
     * @return
     */
    public Set<Department> findAllByIdIn(List<Long> idList){
        return departmentRepository.findByIdIn(idList);
    }

    /**
     * 基于分页的动态查询
     * @param departmentDTO
     * @param pageable
     * @return
     */
    public Page<Department> findByDynamicQuery(Pageable pageable, DepartmentDTO departmentDTO){
        return departmentRepository.findAll(getSpecification(departmentDTO),pageable);
    }

    /**
     * 获取动态查询的条件
     * @param departmentDTO
     * @return
     */
    private Specification<Department> getSpecification(DepartmentDTO departmentDTO){
        Specification<Department> specification = Specification
                .where(StringUtils.isBlank(departmentDTO.getName())?null:departmentRepository.blurStrQuery("name",departmentDTO.getName()))
                .and(StringUtils.isBlank(departmentDTO.getCode())?null:departmentRepository.blurStrQuery("code",departmentDTO.getCode()))
                .and(StringUtils.isBlank(departmentDTO.getType())?null:departmentRepository.blurStrQuery("type",departmentDTO.getType()))
                ;
        return specification;
    }

    public List<DepartmentDTO> getDepartmentDtoList(List<DepartmentDTO> departmentDTOList){
        TreeConvert treeUtils = new TreeConvert(departmentDTOList);
        try {
            List<DepartmentDTO> departmentDTOS = treeUtils.toJsonArray(DepartmentDTO.class);
            return departmentDTOS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void beforeUpdate(DepartmentDTO departmentDTO, Department department) {
        if (StringUtils.isNotBlank(departmentDTO.getParentId())){
            Optional<Department> parentOptional = this.findById(Long.valueOf(departmentDTO.getParentId()));
            if (parentOptional.isPresent()){
                department.setParent(parentOptional.get());
            }
        }
        if (StringUtils.isNotBlank(departmentDTO.getEnterpriseId())){
            Optional<Enterprise> enterpriseOptional = enterpriseService.findById(Long.valueOf(departmentDTO.getEnterpriseId()));
            if (enterpriseOptional.isPresent()){
                department.setEnterprise(enterpriseOptional.get());
            }
        }
    }
}
