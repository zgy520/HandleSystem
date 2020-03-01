package com.zgy.handle.knowledge.service.file;

import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.file.File;
import com.zgy.handle.knowledge.model.file.FileDTO;
import com.zgy.handle.knowledge.repository.KnowledgeRepository;
import com.zgy.handle.knowledge.repository.file.FileRepository;
import com.zgy.handle.knowledge.service.KnowledgeService;
import com.zgy.handle.knowledge.service.catalog.CatalogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FileService extends KnowledgeService<File, FileDTO> {
    private FileRepository fileRepository;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    public FileService(FileRepository fileRepository) {
        super(fileRepository);
        this.fileRepository = fileRepository;
    }

    @Override
    public Page<File> findByDynamicQuery(Pageable pageable, FileDTO dto) {
        Specification<File> specification = Specification
                .where(StringUtils.isBlank(dto.getName())?null:FileRepository.blurStrQuery("name",dto.getName()))
                .and(StringUtils.isBlank(dto.getFileType())?null:FileRepository.blurStrQuery("fileType",dto.getFileType()))
                .and(StringUtils.isBlank(dto.getCatalogName())?null:FileRepository.findByCatalogName(dto.getCatalogName()));
        return fileRepository.findAll(specification,pageable);
    }

    @Override
    public void beforeUpdate(FileDTO fileDTO, File file) {
        if (StringUtils.isNotBlank(fileDTO.getCatalogId())){
            Optional<Catalog> catalogOptional = catalogService.findById(Long.valueOf(fileDTO.getCatalogId()));
            if (catalogOptional.isPresent()){
                file.setCatalog(catalogOptional.get());
            }
        }
    }
}
