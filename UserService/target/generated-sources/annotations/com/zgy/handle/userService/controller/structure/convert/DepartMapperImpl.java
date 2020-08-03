package com.zgy.handle.userService.controller.structure.convert;

import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-03T21:50:50+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class DepartMapperImpl implements DepartMapper {

    @Override
    public Department toDepartment(DepartmentDTO industryDTO) {
        if ( industryDTO == null ) {
            return null;
        }

        Department department = new Department();

        if ( industryDTO.getId() != null ) {
            department.setId( Long.parseLong( industryDTO.getId() ) );
        }
        department.setNote( industryDTO.getNote() );
        department.setCode( industryDTO.getCode() );
        department.setName( industryDTO.getName() );
        department.setType( industryDTO.getType() );

        return department;
    }

    @Override
    public DepartmentDTO toDepartmentDTO(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        Long id = departmentParentId( department );
        if ( id != null ) {
            departmentDTO.setParentId( String.valueOf( id ) );
        }
        if ( department.getId() != null ) {
            departmentDTO.setId( String.valueOf( department.getId() ) );
        }
        departmentDTO.setName( department.getName() );
        departmentDTO.setCode( department.getCode() );
        departmentDTO.setType( department.getType() );
        departmentDTO.setNote( department.getNote() );

        return departmentDTO;
    }

    @Override
    public List<DepartmentDTO> toDepartmentDTOs(List<Department> industryList) {
        if ( industryList == null ) {
            return null;
        }

        List<DepartmentDTO> list = new ArrayList<DepartmentDTO>( industryList.size() );
        for ( Department department : industryList ) {
            list.add( toDepartmentDTO( department ) );
        }

        return list;
    }

    private Long departmentParentId(Department department) {
        if ( department == null ) {
            return null;
        }
        Department parent = department.getParent();
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
