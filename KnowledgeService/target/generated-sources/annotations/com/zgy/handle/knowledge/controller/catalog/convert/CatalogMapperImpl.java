package com.zgy.handle.knowledge.controller.catalog.convert;

import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.catalog.CatalogDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-06T22:16:13+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class CatalogMapperImpl implements CatalogMapper {

    @Override
    public CatalogDTO toCatalogDTO(Catalog catalog) {
        if ( catalog == null ) {
            return null;
        }

        CatalogDTO catalogDTO = new CatalogDTO();

        Long id = catalogParentId( catalog );
        if ( id != null ) {
            catalogDTO.setParentId( String.valueOf( id ) );
        }
        if ( catalog.getId() != null ) {
            catalogDTO.setId( String.valueOf( catalog.getId() ) );
        }
        catalogDTO.setName( catalog.getName() );
        catalogDTO.setDescription( catalog.getDescription() );
        catalogDTO.setNote( catalog.getNote() );

        return catalogDTO;
    }

    @Override
    public List<CatalogDTO> toCatalogDTOS(List<Catalog> catalogList) {
        if ( catalogList == null ) {
            return null;
        }

        List<CatalogDTO> list = new ArrayList<CatalogDTO>( catalogList.size() );
        for ( Catalog catalog : catalogList ) {
            list.add( toCatalogDTO( catalog ) );
        }

        return list;
    }

    @Override
    public Catalog toCatalog(CatalogDTO catalogDTO) {
        if ( catalogDTO == null ) {
            return null;
        }

        Catalog catalog = new Catalog();

        if ( catalogDTO.getId() != null ) {
            catalog.setId( Long.parseLong( catalogDTO.getId() ) );
        }
        catalog.setNote( catalogDTO.getNote() );
        catalog.setName( catalogDTO.getName() );
        catalog.setDescription( catalogDTO.getDescription() );

        return catalog;
    }

    private Long catalogParentId(Catalog catalog) {
        if ( catalog == null ) {
            return null;
        }
        Catalog parent = catalog.getParent();
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
