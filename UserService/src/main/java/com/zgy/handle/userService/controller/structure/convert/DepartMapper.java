package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartMapper {
    Department toDepartment(DepartmentDTO industryDTO);
    DepartmentDTO toDepartmentDTO(Department department);
    List<DepartmentDTO> toDepartmentDTOs(List<Department> industryList);
}
