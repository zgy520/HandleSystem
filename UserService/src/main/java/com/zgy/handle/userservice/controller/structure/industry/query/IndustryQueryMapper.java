package com.zgy.handle.userservice.controller.structure.industry.query;

import com.zgy.handle.userservice.model.dto.structure.IndustryQueryDTO;
import com.zgy.handle.userservice.model.structure.Industry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndustryQueryMapper {
    Industry toIndustry(IndustryQueryDTO industryQueryDTO);
    @Mapping(source = "parent.id", target = "parentId")
    IndustryQueryDTO toIndustryDTO(Industry industry);
    List<IndustryQueryDTO> toIndustryDTOList(List<Industry> industries);
}
