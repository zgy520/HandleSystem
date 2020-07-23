package com.zgy.handle.userService.controller.structure.depart.update;

import com.zgy.handle.userService.model.authority.depart.DepartUpdateDTO;
import com.zgy.handle.userService.model.structure.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartUpdateMapper {
    Department toDepartment(DepartUpdateDTO departUpdateDTO);
    @Mapping(source = "parent.id",target = "parentId")
    DepartUpdateDTO toDepartUpdateDTO(Department department);
    List<DepartUpdateDTO> toDepartUpdateDTOs(List<Department> industryList);
}
