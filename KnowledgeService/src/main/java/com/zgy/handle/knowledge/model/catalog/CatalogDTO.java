package com.zgy.handle.knowledge.model.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CatalogDTO {
    private String id;
    private String name;
    private String description;
    private String note;
    private String parentId;
    private Set<CatalogDTO> children = new HashSet<>();
}
