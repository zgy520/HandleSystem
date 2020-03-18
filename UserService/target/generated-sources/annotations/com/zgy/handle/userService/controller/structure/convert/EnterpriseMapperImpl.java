package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-19T07:53:29+0800",
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
        enterprise.setType( enterpriseDTO.getType() );
        enterprise.setProvince( enterpriseDTO.getProvince() );
        enterprise.setRegCatalog( enterpriseDTO.getRegCatalog() );
        enterprise.setRegCatalogType( enterpriseDTO.getRegCatalogType() );
        enterprise.setPerson( enterpriseDTO.getPerson() );
        enterprise.setPhone( enterpriseDTO.getPhone() );
        enterprise.setEmail( enterpriseDTO.getEmail() );
        enterprise.setIndustry( enterpriseDTO.getIndustry() );
        enterprise.setUec( enterpriseDTO.getUec() );
        enterprise.setUecDate( enterpriseDTO.getUecDate() );
        enterprise.setCheckStatus( enterpriseDTO.getCheckStatus() );
        enterprise.setAuthorStatus( enterpriseDTO.getAuthorStatus() );
        enterprise.setPreGQStatus( enterpriseDTO.getPreGQStatus() );
        enterprise.setPrePauseStatus( enterpriseDTO.getPrePauseStatus() );
        enterprise.setCheckPerson( enterpriseDTO.getCheckPerson() );
        enterprise.setCheckDate( enterpriseDTO.getCheckDate() );
        enterprise.setPreEffectiveDate( enterpriseDTO.getPreEffectiveDate() );
        enterprise.setRegDate( enterpriseDTO.getRegDate() );

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
        enterpriseDTO.setType( enterprise.getType() );
        enterpriseDTO.setProvince( enterprise.getProvince() );
        enterpriseDTO.setRegCatalog( enterprise.getRegCatalog() );
        enterpriseDTO.setRegCatalogType( enterprise.getRegCatalogType() );
        enterpriseDTO.setPerson( enterprise.getPerson() );
        enterpriseDTO.setPhone( enterprise.getPhone() );
        enterpriseDTO.setEmail( enterprise.getEmail() );
        enterpriseDTO.setIndustry( enterprise.getIndustry() );
        enterpriseDTO.setUec( enterprise.getUec() );
        enterpriseDTO.setUecDate( enterprise.getUecDate() );
        enterpriseDTO.setCheckStatus( enterprise.getCheckStatus() );
        enterpriseDTO.setAuthorStatus( enterprise.getAuthorStatus() );
        enterpriseDTO.setPreGQStatus( enterprise.getPreGQStatus() );
        enterpriseDTO.setPrePauseStatus( enterprise.getPrePauseStatus() );
        enterpriseDTO.setCheckPerson( enterprise.getCheckPerson() );
        enterpriseDTO.setCheckDate( enterprise.getCheckDate() );
        enterpriseDTO.setPreEffectiveDate( enterprise.getPreEffectiveDate() );
        enterpriseDTO.setRegDate( enterprise.getRegDate() );
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
