package com.zgy.handle.knowledge.model.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private String id;
    private String name;
    private String note;
    private String fileType;
    private String filePath;
    private String catalogName;
    private String catalogId;
    private List<String> fileIdList = new ArrayList<>();
}
