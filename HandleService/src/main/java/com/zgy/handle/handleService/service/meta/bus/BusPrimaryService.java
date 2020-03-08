package com.zgy.handle.handleService.service.meta.bus;

import com.zgy.handle.handleService.model.meta.bus.BusPrimary;
import com.zgy.handle.handleService.repository.meta.bus.BusPrimaryRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusPrimaryService extends SystemService<BusPrimary,BusPrimary> {
    private BusPrimaryRepository busPrimaryRepository;
    @Autowired
    public BusPrimaryService(BusPrimaryRepository busPrimaryRepository) {
        super(busPrimaryRepository);
        this.busPrimaryRepository = busPrimaryRepository;
    }
}
