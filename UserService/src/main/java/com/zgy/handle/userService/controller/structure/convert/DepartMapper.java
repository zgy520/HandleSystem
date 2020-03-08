package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartMapper {
    Department toDepartment(DepartmentDTO industryDTO);
    @Mapping(source = "parent.id",target = "parentId")
    DepartmentDTO toDepartmentDTO(Department department);
    List<DepartmentDTO> toDepartmentDTOs(List<Department> industryList);
}
