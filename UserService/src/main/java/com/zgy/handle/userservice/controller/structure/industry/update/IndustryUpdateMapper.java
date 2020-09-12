package com.zgy.handle.userservice.controller.structure.industry.update;

import com.zgy.handle.userservice.model.dto.structure.IndustryUpdateDTO;
import com.zgy.handle.userservice.model.structure.Industry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndustryUpdateMapper {
    Industry toIndustry(IndustryUpdateDTO industryUpdateDTO);
    @Mapping(source = "parent.id",target = "parentId")
    IndustryUpdateDTO toIndustryUpdateDto(Industry industry);
    List<IndustryUpdateDTO> toIndustryUpdateDtoList(List<Industry> industryList);
}
