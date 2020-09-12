package com.zgy.handle.userservice.controller.structure.enterprise.convert;

import com.zgy.handle.userservice.model.dto.structure.EnterpriseQueryDTO;
import com.zgy.handle.userservice.model.structure.Enterprise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseQueryMapper {
    Enterprise toEnterprise(EnterpriseQueryDTO enterpriseQueryDTO);

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "industry.id", target = "industryId")
    EnterpriseQueryDTO toEnterpriseQueryDTO(Enterprise enterprise);

    List<EnterpriseQueryDTO> toEnterpriseQueryDTOList(List<Enterprise> enterpriseList);

    List<Enterprise> toEnterpriseList(List<EnterpriseQueryDTO> enterpriseQueryDTOS);
}
