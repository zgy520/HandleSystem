package com.zgy.handle.handleService.repository.meta.structure;

import com.zgy.handle.handleService.model.meta.structure.industry.IndustryMetaBody;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryMetaBodyRepository extends SystemRepository<IndustryMetaBody> {
    List<IndustryMetaBody> findByIndustryMetaHeaderId(Long metaHeaderId);
}
