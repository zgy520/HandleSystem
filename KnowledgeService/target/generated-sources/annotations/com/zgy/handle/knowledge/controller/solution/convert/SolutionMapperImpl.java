package com.zgy.handle.knowledge.controller.solution.convert;

import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.solution.Solution;
import com.zgy.handle.knowledge.model.solution.SolutionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-08T10:34:43+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class SolutionMapperImpl implements SolutionMapper {

    @Override
    public SolutionDTO toSolutionDTO(Solution solution) {
        if ( solution == null ) {
            return null;
        }

        SolutionDTO solutionDTO = new SolutionDTO();

        solutionDTO.setCatalogName( solutionCatalogName( solution ) );
        Long id = solutionCatalogId( solution );
        if ( id != null ) {
            solutionDTO.setCatalogId( String.valueOf( id ) );
        }
        if ( solution.getId() != null ) {
            solutionDTO.setId( String.valueOf( solution.getId() ) );
        }
        solutionDTO.setQuestion( solution.getQuestion() );
        solutionDTO.setMeasure( solution.getMeasure() );
        solutionDTO.setThinking( solution.getThinking() );
        solutionDTO.setNote( solution.getNote() );
        solutionDTO.setUrl( solution.getUrl() );

        return solutionDTO;
    }

    @Override
    public List<SolutionDTO> toSolutionDTOS(List<Solution> solutions) {
        if ( solutions == null ) {
            return null;
        }

        List<SolutionDTO> list = new ArrayList<SolutionDTO>( solutions.size() );
        for ( Solution solution : solutions ) {
            list.add( toSolutionDTO( solution ) );
        }

        return list;
    }

    @Override
    public Solution toSolution(SolutionDTO solutionDTO) {
        if ( solutionDTO == null ) {
            return null;
        }

        Solution solution = new Solution();

        if ( solutionDTO.getId() != null ) {
            solution.setId( Long.parseLong( solutionDTO.getId() ) );
        }
        solution.setNote( solutionDTO.getNote() );
        solution.setQuestion( solutionDTO.getQuestion() );
        solution.setMeasure( solutionDTO.getMeasure() );
        solution.setThinking( solutionDTO.getThinking() );
        solution.setUrl( solutionDTO.getUrl() );

        return solution;
    }

    private String solutionCatalogName(Solution solution) {
        if ( solution == null ) {
            return null;
        }
        Catalog catalog = solution.getCatalog();
        if ( catalog == null ) {
            return null;
        }
        String name = catalog.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long solutionCatalogId(Solution solution) {
        if ( solution == null ) {
            return null;
        }
        Catalog catalog = solution.getCatalog();
        if ( catalog == null ) {
            return null;
        }
        Long id = catalog.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
