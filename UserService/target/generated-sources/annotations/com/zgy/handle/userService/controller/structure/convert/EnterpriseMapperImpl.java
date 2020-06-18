package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-09T21:36:44+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class EnterpriseMapperImpl implements EnterpriseMapper {

    @Override
    public Enterprise toEnterprise(EnterpriseDTO enterpriseDTO) {
        if ( enterpriseDTO == null ) {
            return null;
        }

        Enterprise enterprise = new Enterprise();

        if ( enterpriseDTO.getId() != null ) {
            enterprise.setId( Long.parseLong( enterpriseDTO.getId() ) );
        }
        enterprise.setNote( enterpriseDTO.getNote() );
        enterprise.setCode( enterpriseDTO.getCode() );
        enterprise.setName( enterpriseDTO.getName() );
        enterprise.setShortName( enterpriseDTO.getShortName() );

        return enterprise;
    }

    @Override
    public EnterpriseDTO toEnterpriseDTO(Enterprise enterprise) {
        if ( enterprise == null ) {
            return null;
        }

        EnterpriseDTO enterpriseDTO = new EnterpriseDTO();

        Long id = enterpriseParentId( enterprise );
        if ( id != null ) {
            enterpriseDTO.setParentId( String.valueOf( id ) );
        }
        if ( enterprise.getId() != null ) {
            enterpriseDTO.setId( String.valueOf( enterprise.getId() ) );
        }
        enterpriseDTO.setCode( enterprise.getCode() );
        enterpriseDTO.setName( enterprise.getName() );
        enterpriseDTO.setShortName( enterprise.getShortName() );
        enterpriseDTO.setNote( enterprise.getNote() );

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

    private Long enterpriseParentId(Enterprise enterprise) {
        if ( enterprise == null ) {
            return null;
        }
        Enterprise parent = enterprise.getParent();
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
