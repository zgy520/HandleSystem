package com.zgy.handle.userservice.controller.structure.convert;

import com.zgy.handle.userservice.model.structure.Enterprise;
import com.zgy.handle.userservice.model.structure.EnterpriseDTO;
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
