package com.zgy.handle.userService.controller.structure;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.structure.convert.DepartMapper;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentDTO;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.structure.DepartmentService;
import com.zgy.handle.userService.service.structure.EnterpriseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "depart")
@Slf4j
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartMapper departMapper;
    private final EnterpriseService enterpriseService;

    @GetMapping(value = "list")
    public ResponseCode<List<DepartmentDTO>> list(DepartmentDTO departmentDTO){
        ResponseCode<List<DepartmentDTO>> responseCode = ResponseCode.sucess();
        responseCode.setData(departmentService.getDepartmentDtoList());
        return responseCode;
    }

    @PostMapping(value = "update")
    public ResponseCode<Department> update(@RequestBody DepartmentDTO departmentDTO){
        ResponseCode<Department> responseCode = ResponseCode.sucess();
        Department department = departMapper.toDepartment(departmentDTO);
        if (StringUtils.isNotBlank(departmentDTO.getParentId())){
            Optional<Department> parentOptional = departmentService.findById(Long.valueOf(departmentDTO.getParentId()));
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
        department = departmentService.update(StringUtils.isBlank(departmentDTO.getId())?null:Long.valueOf(departmentDTO.getId()),department);
        responseCode.setData(department);
        return responseCode;
    }
    @DeleteMapping(value = "delete/{id}")
    public ResponseCode<DepartmentDTO> delete(@PathVariable("id") Long id){
        ResponseCode<DepartmentDTO> responseCode = ResponseCode.sucess();
        Optional<Department> optionalEnterprise = departmentService.findById(id);
        if (optionalEnterprise.isPresent()){
            responseCode.setData(departMapper.toDepartmentDTO(optionalEnterprise.get()));
        }else {
            throw new EntityNotFoundException("找不到id为:" + id + "的数据");
        }
        departmentService.delete(id);
        return responseCode;
    }

    /**
     * 获取所有的企业列表，用于下拉框
     * @return
     */
    @GetMapping(value = "getDepartList")
    public ResponseCode<List<SelectDTO>> getDepartList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Department> departmentList = departmentService.findAll();
        List<SelectDTO> selectDTOList = new ArrayList<>();
        departmentList.stream().forEach(department -> {
            SelectDTO selectDTO = new SelectDTO(department.getId().toString(),department.getName());
            selectDTOList.add(selectDTO);
        });
        responseCode.setData(selectDTOList);
        return responseCode;
    }
}
