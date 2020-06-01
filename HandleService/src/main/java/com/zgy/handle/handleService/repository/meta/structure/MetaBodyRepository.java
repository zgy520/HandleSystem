package com.zgy.handle.handleService.repository.meta.structure;

import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetaBodyRepository extends SystemRepository<MetaBody> {
    List<MetaBody> findByMetaHeaderId(Long metaHeaderId);
    Page<MetaBody> findByMetaHeaderId(Long metaHeaderId, Pageable pageable);
}
