package com.zgy.handle.knowledge.controller.linkpage.convert;

import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.linkpage.LinkPage;
import com.zgy.handle.knowledge.model.linkpage.LinkPageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-04T19:46:27+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class LinkPageMapperImpl implements LinkPageMapper {

    @Override
    public LinkPageDTO toLinkPageDTO(LinkPage linkPage) {
        if ( linkPage == null ) {
            return null;
        }

        LinkPageDTO linkPageDTO = new LinkPageDTO();

        linkPageDTO.setCatalogName( linkPageCatalogName( linkPage ) );
        Long id = linkPageCatalogId( linkPage );
        if ( id != null ) {
            linkPageDTO.setCatalogId( String.valueOf( id ) );
        }
        if ( linkPage.getId() != null ) {
            linkPageDTO.setId( String.valueOf( linkPage.getId() ) );
        }
        linkPageDTO.setTitle( linkPage.getTitle() );
        linkPageDTO.setKeywords( linkPage.getKeywords() );
        linkPageDTO.setLinkUrl( linkPage.getLinkUrl() );
        linkPageDTO.setNote( linkPage.getNote() );

        return linkPageDTO;
    }

    @Override
    public List<LinkPageDTO> toLinkPageDTOS(List<LinkPage> linkPageList) {
        if ( linkPageList == null ) {
            return null;
        }

        List<LinkPageDTO> list = new ArrayList<LinkPageDTO>( linkPageList.size() );
        for ( LinkPage linkPage : linkPageList ) {
            list.add( toLinkPageDTO( linkPage ) );
        }

        return list;
    }

    @Override
    public LinkPage toLinkPage(LinkPageDTO linkPageDTO) {
        if ( linkPageDTO == null ) {
            return null;
        }

        LinkPage linkPage = new LinkPage();

        if ( linkPageDTO.getId() != null ) {
            linkPage.setId( Long.parseLong( linkPageDTO.getId() ) );
        }
        linkPage.setNote( linkPageDTO.getNote() );
        linkPage.setTitle( linkPageDTO.getTitle() );
        linkPage.setKeywords( linkPageDTO.getKeywords() );
        linkPage.setLinkUrl( linkPageDTO.getLinkUrl() );

        return linkPage;
    }

    private String linkPageCatalogName(LinkPage linkPage) {
        if ( linkPage == null ) {
            return null;
        }
        Catalog catalog = linkPage.getCatalog();
        if ( catalog == null ) {
            return null;
        }
        String name = catalog.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long linkPageCatalogId(LinkPage linkPage) {
        if ( linkPage == null ) {
            return null;
        }
        Catalog catalog = linkPage.getCatalog();
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
