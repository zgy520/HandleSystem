package com.zgy.handle.knowledge.service.file;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.knowledge.model.file.File;
import com.zgy.handle.knowledge.repository.file.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileStorageService {
    @Autowired
    private FileRepository fileRepository;

    public File storeFile(MultipartFile file){
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")){
                throw new Exception("Sorry! Filename contains invalid path sequecnce " + fileName);
            }
            File file1 = new File();
            file1.setName(fileName);
            file1.setFileType(file.getContentType());
            file1.setData(file.getBytes());
            return fileRepository.save(file1);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public File getFile(String fileId){
        Optional<File> fileOptional = fileRepository.findById(Long.valueOf(fileId));
        if (fileOptional.isPresent()){
            return fileOptional.get();
        }else {
            throw new EntityNotFoundException("未发现id为:" + fileId + "得文件");
        }
    }
}
