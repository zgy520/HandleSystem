package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseRegDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseRegMapper {
    Enterprise toEnterprise(EnterpriseRegDTO enterpriseRegDTO);
    @Mapping(source = "parent.id", target = "parentId")
    EnterpriseRegDTO toEnterpriseRegDTO(Enterprise enterprise);
    List<EnterpriseRegDTO> toEnterpriseRegDTOs(List<Enterprise> enterpriseList);
}
