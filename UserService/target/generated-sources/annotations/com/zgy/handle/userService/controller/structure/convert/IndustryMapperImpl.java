package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.structure.IndustryDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-24T21:10:34+0800",
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

        if ( industry.getId() != null ) {
            industryDTO.setId( String.valueOf( industry.getId() ) );
        }
        industryDTO.setName( industry.getName() );
        industryDTO.setCode( industry.getCode() );
        industryDTO.setShortName( industry.getShortName() );

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
}