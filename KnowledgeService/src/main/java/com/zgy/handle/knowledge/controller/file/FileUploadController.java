package com.zgy.handle.knowledge.controller.file;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.knowledge.model.file.File;
import com.zgy.handle.knowledge.model.file.FileDTO;
import com.zgy.handle.knowledge.model.file.UploadFileResponse;
import com.zgy.handle.knowledge.service.file.FileService;
import com.zgy.handle.knowledge.service.file.FileStorageService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "upload")
@Slf4j
public class FileUploadController {
    private static final String filePath = "E:\\pad";
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private FileService fileService;

    @ApiOperation(value = "单文件上传")
    @PostMapping(value = "file")
    public ResponseCode<UploadFileResponse> upload(MultipartFile file){
        ResponseCode<UploadFileResponse> responseCode = new ResponseCode<>();
        responseCode.setData(uploadFileResponses(file));
        return responseCode;

    }

    private UploadFileResponse uploadFileResponses(MultipartFile file){
        File file1 = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("downloadFile/")
                .path(file1.getId().toString())
                .toUriString();
        UploadFileResponse uploadFileResponse = new UploadFileResponse(file1.getId().toString(),file1.getName(),fileDownloadUri,file1.getFileType());
        return uploadFileResponse;
    }

    @PostMapping(value = "multipleFiles")
    @ApiOperation(value = "多文件上传")
    public ResponseCode<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
        ResponseCode<List<UploadFileResponse>> responseCode = ResponseCode.sucess();
        List<UploadFileResponse> list = Arrays.asList(files)
                .stream()
                .map(file->uploadFileResponses(file))
                .collect(Collectors.toList());
        responseCode.setData(list);
        return responseCode;
    }

    @GetMapping("/downloadFile/{fileId}")
    @ApiOperation("文件下载")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws UnsupportedEncodingException {
        File file = fileStorageService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + new String(file.getName().getBytes("UTF-8"),"iso-8859-1") + "\"")
                .body(new ByteArrayResource(fileService.fetchFileData(Long.valueOf(fileId))));
    }


}
