package com.zgy.handle.userService.controller.structure;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.structure.convert.DepartMapper;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.structure.IndustryDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.structure.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "depart")
@Slf4j
public class DepartmentController extends SystemController<Department,DepartmentDTO> {
    private DepartmentService departmentService;
    @Autowired
    private DepartMapper departMapper;

    public DepartmentController(DepartmentService departmentService) {
        super(departmentService);
        this.departmentService = departmentService;
    }


    @Override
    public ResponseCode<List<DepartmentDTO>> list(Pageable pageable, DepartmentDTO dto) {
        ResponseCode<List<DepartmentDTO>> responseCode = ResponseCode.sucess();
        List<Department> departmentList = departmentService.findAll();
        List<DepartmentDTO> departmentDTOList = departMapper.toDepartmentDTOs(departmentList);
        responseCode.setData(departmentService.getDepartmentDtoList(departmentDTOList));
        return responseCode;
    }

    @Override
    public List<DepartmentDTO> convertTtoU(List<Department> departments) {
        return departMapper.toDepartmentDTOs(departments);
    }

    @Override
    public DepartmentDTO convertTtoU(Department department) {
        return departMapper.toDepartmentDTO(department);
    }

    @Override
    public Department convertUtoT(DepartmentDTO departmentDTO) {
        return departMapper.toDepartment(departmentDTO);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Department> departmentList) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        departmentList.stream().forEach(department -> {
            SelectDTO selectDTO = new SelectDTO(department.getId().toString(),department.getName(),department.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
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
            SelectDTO selectDTO = new SelectDTO(department.getId().toString(),department.getName(),department.getId().toString());
            selectDTOList.add(selectDTO);
        });
        responseCode.setData(selectDTOList);
        return responseCode;
    }
}
