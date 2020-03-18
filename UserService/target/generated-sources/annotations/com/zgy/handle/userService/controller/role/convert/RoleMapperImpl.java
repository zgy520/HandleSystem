package com.zgy.handle.userService.controller.role.convert;

import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.RoleDTO;
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
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO toRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        if ( role.getId() != null ) {
            roleDTO.setId( String.valueOf( role.getId() ) );
        }
        roleDTO.setCode( role.getCode() );
        roleDTO.setName( role.getName() );
        roleDTO.setNote( role.getNote() );

        return roleDTO;
    }

    @Override
    public List<RoleDTO> toRoleDTOs(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( roles.size() );
        for ( Role role : roles ) {
            list.add( toRoleDTO( role ) );
        }

        return list;
    }

    @Override
    public Role toRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        if ( roleDTO.getId() != null ) {
            role.setId( Long.parseLong( roleDTO.getId() ) );
        }
        role.setNote( roleDTO.getNote() );
        role.setCode( roleDTO.getCode() );
        role.setName( roleDTO.getName() );

        return role;
    }
}
