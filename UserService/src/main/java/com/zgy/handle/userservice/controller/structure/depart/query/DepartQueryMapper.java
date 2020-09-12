package com.zgy.handle.userservice.controller.structure.depart.query;

import com.zgy.handle.userservice.model.authority.depart.DepartQueryDTO;
import com.zgy.handle.userservice.model.structure.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartQueryMapper {
    Department toDepartment(DepartQueryDTO departQueryDTO);
    @Mapping(source = "parent.name",target = "parentName")
    @Mapping(source = "parent.id",target = "parentId")
    DepartQueryDTO toDepartQueryDTO(Department department);
    List<DepartQueryDTO> toDepartQueryDTOs(List<Department> industryList);
}
