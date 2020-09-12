package com.zgy.handle.userservice.controller.structure.convert;

import com.zgy.handle.userservice.model.structure.Industry;
import com.zgy.handle.userservice.model.structure.IndustryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndustryMapper {

    Industry toIndustry(IndustryDTO industryDTO);

    @Mapping(source = "parent.id",target = "parentId")
    IndustryDTO toIndustryDTO(Industry industry);

    List<IndustryDTO> toIndustryDTOs(List<Industry> enterpriseList);
}
