package com.zgy.handle.handleService.service.meta.bus;

import com.zgy.handle.handleService.model.meta.bus.BusDetails;
import com.zgy.handle.handleService.repository.meta.bus.BusDetailsRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusDetailsService extends SystemService<BusDetails,BusDetails> {
    private BusDetailsRepository busDetailsRepository;
    @Autowired
    public BusDetailsService(BusDetailsRepository busDetailsRepository) {
        super(busDetailsRepository);
        this.busDetailsRepository = busDetailsRepository;
    }
}
