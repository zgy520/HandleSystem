package com.zgy.handle.knowledge.service.solution;

import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.common.ResourceType;
import com.zgy.handle.knowledge.model.solution.Solution;
import com.zgy.handle.knowledge.model.solution.SolutionDTO;
import com.zgy.handle.knowledge.repository.solution.SolutionRepository;
import com.zgy.handle.knowledge.service.KnowledgeService;
import com.zgy.handle.knowledge.service.catalog.CatalogService;
import com.zgy.handle.knowledge.service.dispatch.ResourceDispatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SolutionService extends KnowledgeService<Solution, SolutionDTO> {
    private SolutionRepository solutionRepository;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private ResourceDispatchService resourceDispatchService;
    @Autowired
    public SolutionService(SolutionRepository solutionRepository) {
        super(solutionRepository);
        this.solutionRepository = solutionRepository;
    }

    @Override
    public Page<Solution> findByDynamicQuery(Pageable pageable, SolutionDTO dto) {
        Specification<Solution> specification = Specification
                .where(StringUtils.isBlank(dto.getQuestion())?null:SolutionRepository.blurStrQuery("question",dto.getQuestion()))
                .and(StringUtils.isBlank(dto.getMeasure())?null:SolutionRepository.blurStrQuery("measure",dto.getMeasure()))
                .and(StringUtils.isBlank(dto.getCatalogName())?null:SolutionRepository.findByCatalogName(dto.getCatalogName()))
                .and(StringUtils.isBlank(dto.getNote())?null:SolutionRepository.blurStrQuery("note",dto.getNote()));
        return solutionRepository.findAll(specification,pageable);
    }

    @Override
    public void beforeUpdate(SolutionDTO solutionDTO, Solution solution) {
        if (StringUtils.isNotBlank(solutionDTO.getCatalogId())){
            Optional<Catalog> catalogOptional = catalogService.findById(Long.valueOf(solutionDTO.getCatalogId()));
            if (catalogOptional.isPresent()){
                solution.setCatalog(catalogOptional.get());
            }
        }
    }

    @Override
    public void postUpdate(Solution solution, SolutionDTO solutionDTO) {
        resourceDispatchService.addResourceDispatch(solution.getId(), ResourceType.SOLUTION);
    }
}
