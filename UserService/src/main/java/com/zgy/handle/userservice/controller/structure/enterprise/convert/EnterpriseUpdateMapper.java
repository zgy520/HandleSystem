package com.zgy.handle.userservice.controller.structure.enterprise.convert;

import com.zgy.handle.userservice.model.dto.structure.EnterpriseUpdateDTO;
import com.zgy.handle.userservice.model.structure.Enterprise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseUpdateMapper {
    Enterprise toEnterprise(EnterpriseUpdateDTO enterpriseUpdateDTO);

    @Mapping(source = "parent.id", target = "parentId")
    EnterpriseUpdateDTO toEnterpriseUpdateDTO(Enterprise enterprise);

    List<EnterpriseUpdateDTO> toEnterpriseUpdateDTOList(List<Enterprise> enterpriseList);

    List<Enterprise> toEnterpriseList(List<EnterpriseUpdateDTO> enterpriseUpdateDTOList);
}
