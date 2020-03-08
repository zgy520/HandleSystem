package com.zgy.handle.knowledge.controller.solution.convert;

import com.zgy.handle.knowledge.model.solution.Solution;
import com.zgy.handle.knowledge.model.solution.SolutionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SolutionMapper {
    @Mapping(source = "catalog.name",target = "catalogName")
    @Mapping(source = "catalog.id",target = "catalogId")
    SolutionDTO toSolutionDTO(Solution solution);
    List<SolutionDTO> toSolutionDTOS(List<Solution> solutions);
    Solution toSolution(SolutionDTO solutionDTO);
}
