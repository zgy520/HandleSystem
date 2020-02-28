package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {
    Enterprise toEnterprise(EnterpriseDTO enterpriseDTO);
    @Mapping(source = "parent.id", target = "parentId")
    EnterpriseDTO toEnterpriseDTO(Enterprise enterprise);
    List<EnterpriseDTO> toEnterpriseDTOs(List<Enterprise> enterpriseList);
}
