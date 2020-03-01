package com.zgy.handle.knowledge.controller.solution;

import com.zgy.handle.knowledge.controller.KnowledgeController;
import com.zgy.handle.knowledge.controller.solution.convert.SolutionMapper;
import com.zgy.handle.knowledge.model.SelectDTO;
import com.zgy.handle.knowledge.model.solution.Solution;
import com.zgy.handle.knowledge.model.solution.SolutionDTO;
import com.zgy.handle.knowledge.service.KnowledgeService;
import com.zgy.handle.knowledge.service.solution.SolutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "solution")
@RestController
@Slf4j
public class SolutionController extends KnowledgeController<Solution, SolutionDTO> {
    private SolutionService solutionService;
    @Autowired
    private SolutionMapper solutionMapper;
    public SolutionController(SolutionService solutionService) {
        super(solutionService);
        this.solutionService = solutionService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Solution> solutions) {
        return null;
    }

    @Override
    public List<SolutionDTO> convertTtoU(List<Solution> solutions) {
        return solutionMapper.toSolutionDTOS(solutions);
    }

    @Override
    public SolutionDTO convertTtoU(Solution solution) {
        return solutionMapper.toSolutionDTO(solution);
    }

    @Override
    public Solution convertUtoT(SolutionDTO solutionDTO) {
        return solutionMapper.toSolution(solutionDTO);
    }
}
