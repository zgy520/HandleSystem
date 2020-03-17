package com.zgy.handle.handleService.repository.meta.bus;

import com.zgy.handle.handleService.model.meta.bus.BusDetails;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusDetailsRepository extends SystemRepository<BusDetails> {
    List<BusDetails> findByBusPrimaryId(Long primaryId);
}
