package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.structure.IndustryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-26T18:49:02+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class IndustryMapperImpl implements IndustryMapper {

    @Override
    public Industry toIndustry(IndustryDTO industryDTO) {
        if ( industryDTO == null ) {
            return null;
        }

        Industry industry = new Industry();

        if ( industryDTO.getId() != null ) {
            industry.setId( Long.parseLong( industryDTO.getId() ) );
        }
        industry.setNote( industryDTO.getNote() );
        industry.setCode( industryDTO.getCode() );
        industry.setName( industryDTO.getName() );
        industry.setShortName( industryDTO.getShortName() );

        return industry;
    }

    @Override
    public IndustryDTO toIndustryDTO(Industry industry) {
        if ( industry == null ) {
            return null;
        }

        IndustryDTO industryDTO = new IndustryDTO();

        Long id = industryParentId( industry );
        if ( id != null ) {
            industryDTO.setParentId( String.valueOf( id ) );
        }
        if ( industry.getId() != null ) {
            industryDTO.setId( String.valueOf( industry.getId() ) );
        }
        industryDTO.setName( industry.getName() );
        industryDTO.setCode( industry.getCode() );
        industryDTO.setShortName( industry.getShortName() );
        industryDTO.setNote( industry.getNote() );

        return industryDTO;
    }

    @Override
    public List<IndustryDTO> toIndustryDTOs(List<Industry> enterpriseList) {
        if ( enterpriseList == null ) {
            return null;
        }

        List<IndustryDTO> list = new ArrayList<IndustryDTO>( enterpriseList.size() );
        for ( Industry industry : enterpriseList ) {
            list.add( toIndustryDTO( industry ) );
        }

        return list;
    }

    private Long industryParentId(Industry industry) {
        if ( industry == null ) {
            return null;
        }
        Industry parent = industry.getParent();
        if ( parent == null ) {
            return null;
        }
        Long id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
