package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentDTO;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.repository.structure.DepartmentRepository;
import com.zgy.handle.userService.service.SystemRefactorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class DepartmentService extends SystemRefactorService<Department,DepartmentDTO> {
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

    public List<DepartmentDTO> getDepartmentDtoList(){
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        // 获取所有的上级机构
        List<Department> parentEnterpriseList = departmentRepository.findByParentIdIsNull();
        // 遍历顶级企业下的所有子企业
        departmentDTOList = getDepartmentDtoList(parentEnterpriseList);
        //log.info("获取到的最终数据为: " + departmentDTOList.toString());
        return departmentDTOList;
    }

    private List<DepartmentDTO> getDepartmentDtoList(List<Department> departmentList){
        List<DepartmentDTO> enterpriseDTOList = new ArrayList<>();
        for (Department department : departmentList){
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setCode(department.getCode());
            departmentDTO.setName(department.getName());
            departmentDTO.setId(department.getId().toString());
            departmentDTO.setNote(department.getNote());
            departmentDTO.setType(department.getType());
            departmentDTO.setParentId(department.getParent()==null?"":department.getParent().getId().toString());
            departmentDTO.setEnterpriseId(department.getEnterprise()==null?"":department.getEnterprise().getId().toString());
            departmentDTO.setChildren(getChildrenEnterprise(department.getId()));
            enterpriseDTOList.add(departmentDTO);
        }
        return enterpriseDTOList;
    }

    /**
     * 获取某一企业下的所有子企业列表
     * @param parentId
     * @return
     */
    private List<DepartmentDTO> getChildrenEnterprise(Long parentId){
        List<Department> chidrenEnterprise = departmentRepository.findByParentId(parentId);
        return getDepartmentDtoList(chidrenEnterprise);
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
