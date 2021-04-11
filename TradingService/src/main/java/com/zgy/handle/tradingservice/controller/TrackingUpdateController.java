package com.zgy.handle.tradingservice.controller;

import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.tradingservice.dto.TrackingDTO;
import com.zgy.handle.tradingservice.mapper.TrackingMapper;
import com.zgy.handle.tradingservice.model.Tracking;
import com.zgy.handle.tradingservice.service.TrackingQueryService;
import com.zgy.handle.tradingservice.service.TrackingUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: a4423
 * @date: 2021/4/9 22:39
 */
@RestController
@RequestMapping(value = "tracking/update")
public class TrackingUpdateController extends BaseUpdateController<Tracking, TrackingDTO> {
    @Autowired
    private TrackingMapper trackingMapper;
    private TrackingQueryService trackingQueryService;
    private TrackingUpdateService trackingUpdateService;
    public TrackingUpdateController(TrackingUpdateService trackingUpdateService, TrackingQueryService trackingQueryService) {
        super(trackingUpdateService, trackingQueryService);
        this.trackingQueryService = trackingQueryService;
        this.trackingUpdateService = trackingUpdateService;
    }

    /**
     * 将dto转为实体
     *
     * @param trackingDTO
     * @return
     */
    @Override
    public Tracking convertUtoT(TrackingDTO trackingDTO) {
        return trackingMapper.toTracking(trackingDTO);
    }
}
