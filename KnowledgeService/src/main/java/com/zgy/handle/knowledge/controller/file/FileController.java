package com.zgy.handle.knowledge.controller.file;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.knowledge.controller.KnowledgeController;
import com.zgy.handle.knowledge.controller.file.convert.FileMapper;
import com.zgy.handle.knowledge.model.SelectDTO;
import com.zgy.handle.knowledge.model.file.File;
import com.zgy.handle.knowledge.model.file.FileDTO;
import com.zgy.handle.knowledge.service.KnowledgeService;
import com.zgy.handle.knowledge.service.file.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "file")
@Slf4j
public class FileController extends KnowledgeController<File, FileDTO> {
    private FileService fileService;
    @Autowired
    private FileMapper fileMapper;
    public FileController(FileService fileService) {
        super(fileService);
        this.fileService = fileService;
    }

    @PostMapping(value = "batchUpdate")
    public ResponseCode<List<FileDTO>> updatex(@RequestBody FileDTO fileDTO){
        ResponseCode<List<FileDTO>> responseCode = fileService.batchUpdate(fileDTO);
        return responseCode;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<File> files) {
        return null;
    }

    @Override
    public List<FileDTO> convertTtoU(List<File> files) {
        return fileMapper.toFileDTOS(files);
    }

    @Override
    public FileDTO convertTtoU(File file) {
        return fileMapper.toFileDTO(file);
    }

    @Override
    public File convertUtoT(FileDTO fileDTO) {
        return fileMapper.toFile(fileDTO);
    }
}
