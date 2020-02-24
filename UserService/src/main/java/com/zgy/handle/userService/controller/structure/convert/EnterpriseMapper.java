package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {
    Enterprise toEnterprise(EnterpriseDTO enterpriseDTO);
    EnterpriseDTO toEnterpriseDTO(Enterprise enterprise);
    List<EnterpriseDTO> toEnterpriseDTOs(List<Enterprise> enterpriseList);
}
