package com.zgy.handle.knowledge.controller.file.convert;

import com.zgy.handle.knowledge.model.file.File;
import com.zgy.handle.knowledge.model.file.FileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {
    @Mapping(source = "catalog.name",target = "catalogName")
    @Mapping(source = "catalog.id",target = "catalogId")
    FileDTO toFileDTO(File file);
    List<FileDTO> toFileDTOS(List<File> fileList);
    File toFile(FileDTO fileDTO);
}
