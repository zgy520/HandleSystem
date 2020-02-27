package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.Enterprise.EnterpriseBuilder;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-24T20:12:46+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class EnterpriseMapperImpl implements EnterpriseMapper {

    @Override
    public Enterprise toEnterprise(EnterpriseDTO enterpriseDTO) {
        if ( enterpriseDTO == null ) {
            return null;
        }

        EnterpriseBuilder enterprise = Enterprise.builder();

        enterprise.code( enterpriseDTO.getCode() );
        enterprise.name( enterpriseDTO.getName() );
        enterprise.shortName( enterpriseDTO.getShortName() );

        return enterprise.build();
    }

    @Override
    public EnterpriseDTO toEnterpriseDTO(Enterprise enterprise) {
        if ( enterprise == null ) {
            return null;
        }

        EnterpriseDTO enterpriseDTO = new EnterpriseDTO();

        if ( enterprise.getId() != null ) {
            enterpriseDTO.setId( String.valueOf( enterprise.getId() ) );
        }
        enterpriseDTO.setCode( enterprise.getCode() );
        enterpriseDTO.setName( enterprise.getName() );
        enterpriseDTO.setShortName( enterprise.getShortName() );

        return enterpriseDTO;
    }

    @Override
    public List<EnterpriseDTO> toEnterpriseDTOs(List<Enterprise> enterpriseList) {
        if ( enterpriseList == null ) {
            return null;
        }

        List<EnterpriseDTO> list = new ArrayList<EnterpriseDTO>( enterpriseList.size() );
        for ( Enterprise enterprise : enterpriseList ) {
            list.add( toEnterpriseDTO( enterprise ) );
        }

        return list;
    }
}