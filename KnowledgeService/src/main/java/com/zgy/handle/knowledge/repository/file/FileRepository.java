package com.zgy.handle.knowledge.repository.file;

import com.zgy.handle.knowledge.model.file.File;
import com.zgy.handle.knowledge.repository.KnowledgeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends KnowledgeRepository<File> {
}
