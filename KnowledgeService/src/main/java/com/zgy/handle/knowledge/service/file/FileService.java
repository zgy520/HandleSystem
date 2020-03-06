package com.zgy.handle.knowledge.service.file;

import com.zgy.handle.common.response.ResponseCode;
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

import java.util.ArrayList;
import java.util.List;
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

    public ResponseCode<List<FileDTO>> batchUpdate(FileDTO fileDTO){
        ResponseCode<List<FileDTO>> responseCode = ResponseCode.sucess();
        List<FileDTO> fileDTOS = new ArrayList<>();
        if (fileDTO.getFileIdList().size() > 0){
            Catalog catalog = null;
            if (StringUtils.isNotBlank(fileDTO.getCatalogId())){
                Optional<Catalog> catalogOptional = catalogService.findById(Long.valueOf(fileDTO.getCatalogId()));
                if (catalogOptional.isPresent()){
                    catalog = catalogOptional.get();
                }
            }
            for (String fileId : fileDTO.getFileIdList()){
                Optional<File> optionalFile = this.findById(Long.valueOf(fileId));
                if (optionalFile.isPresent()){
                    File file = optionalFile.get();
                    file.setNote(fileDTO.getNote());
                    if (catalog != null)
                        file.setCatalog(catalog);
                    fileRepository.save(file);
                }
            }

        }
        responseCode.setData(fileDTOS);
        return responseCode;
    }

}
