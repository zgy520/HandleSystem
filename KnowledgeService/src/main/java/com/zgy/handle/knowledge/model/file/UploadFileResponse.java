package com.zgy.handle.knowledge.model.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponse {
    private String fileId;
    private String fileName;
    private String downloadUri;
    private String contentType;
}
