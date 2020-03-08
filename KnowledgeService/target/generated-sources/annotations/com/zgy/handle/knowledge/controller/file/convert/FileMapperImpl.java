package com.zgy.handle.knowledge.controller.file.convert;

import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.file.File;
import com.zgy.handle.knowledge.model.file.FileDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-07T11:00:41+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class FileMapperImpl implements FileMapper {

    @Override
    public FileDTO toFileDTO(File file) {
        if ( file == null ) {
            return null;
        }

        FileDTO fileDTO = new FileDTO();

        fileDTO.setCatalogName( fileCatalogName( file ) );
        Long id = fileCatalogId( file );
        if ( id != null ) {
            fileDTO.setCatalogId( String.valueOf( id ) );
        }
        if ( file.getId() != null ) {
            fileDTO.setId( String.valueOf( file.getId() ) );
        }
        fileDTO.setName( file.getName() );
        fileDTO.setNote( file.getNote() );
        fileDTO.setFileType( file.getFileType() );
        fileDTO.setFilePath( file.getFilePath() );

        return fileDTO;
    }

    @Override
    public List<FileDTO> toFileDTOS(List<File> fileList) {
        if ( fileList == null ) {
            return null;
        }

        List<FileDTO> list = new ArrayList<FileDTO>( fileList.size() );
        for ( File file : fileList ) {
            list.add( toFileDTO( file ) );
        }

        return list;
    }

    @Override
    public File toFile(FileDTO fileDTO) {
        if ( fileDTO == null ) {
            return null;
        }

        File file = new File();

        if ( fileDTO.getId() != null ) {
            file.setId( Long.parseLong( fileDTO.getId() ) );
        }
        file.setNote( fileDTO.getNote() );
        file.setName( fileDTO.getName() );
        file.setFileType( fileDTO.getFileType() );
        file.setFilePath( fileDTO.getFilePath() );

        return file;
    }

    private String fileCatalogName(File file) {
        if ( file == null ) {
            return null;
        }
        Catalog catalog = file.getCatalog();
        if ( catalog == null ) {
            return null;
        }
        String name = catalog.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Long fileCatalogId(File file) {
        if ( file == null ) {
            return null;
        }
        Catalog catalog = file.getCatalog();
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
