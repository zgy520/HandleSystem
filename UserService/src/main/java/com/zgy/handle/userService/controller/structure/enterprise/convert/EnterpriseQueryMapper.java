package com.zgy.handle.userService.controller.structure.enterprise.convert;

import com.zgy.handle.userService.model.dto.structure.EnterpriseQueryDTO;
import com.zgy.handle.userService.model.structure.Enterprise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseQueryMapper {
    Enterprise toEnterprise(EnterpriseQueryDTO enterpriseQueryDTO);

    @Mapping(source = "parent.id", target = "parentId")
    EnterpriseQueryDTO toEnterpriseQueryDTO(Enterprise enterprise);

    List<EnterpriseQueryDTO> toEnterpriseQueryDTOList(List<Enterprise> enterpriseList);

    List<Enterprise> toEnterpriseList(List<EnterpriseQueryDTO> enterpriseQueryDTOS);
}
