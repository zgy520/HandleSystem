package com.zgy.handle.handleService.repository.meta.bus;

import com.zgy.handle.handleService.model.meta.bus.BusPrimary;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BusPrimaryRepository extends SystemRepository<BusPrimary> {
    List<BusPrimary> findByMetaHeaderId(Long metaHeaderId);

    @Transactional
    @Modifying
    Integer deleteByMetaHeaderId(Long headerId);
}
