package com.zgy.handle.userService.controller.structure;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.structure.convert.DepartMapper;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentDTO;
import com.zgy.handle.userService.service.structure.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "depart")
@Slf4j
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartMapper departMapper;

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
        if (departmentDTO.getParentId() != null){
            Optional<Department> parentOptional = departmentService.findById(departmentDTO.getParentId());
            if (parentOptional.isPresent()){
                department.setParent(parentOptional.get());
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
}
