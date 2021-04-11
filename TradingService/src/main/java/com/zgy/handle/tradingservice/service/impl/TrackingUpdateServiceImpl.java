package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.tradingservice.dto.TrackingDTO;
import com.zgy.handle.tradingservice.model.Tracking;
import com.zgy.handle.tradingservice.repository.TrackingUpdateRepository;
import com.zgy.handle.tradingservice.service.TrackingUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2021/4/8 22:58
 */
@Service
@Slf4j
public class TrackingUpdateServiceImpl extends BaseUpdateServiceImpl<Tracking, TrackingDTO> implements TrackingUpdateService {
    private TrackingUpdateRepository trackingUpdateRepository;
    public TrackingUpdateServiceImpl(TrackingUpdateRepository trackingUpdateRepository) {
        super(trackingUpdateRepository);
        this.trackingUpdateRepository = trackingUpdateRepository;
    }
}
