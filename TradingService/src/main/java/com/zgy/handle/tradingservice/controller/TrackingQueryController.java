package com.zgy.handle.tradingservice.controller;

import com.zgy.handle.common.controller.base.BaseQueryController;
import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.tradingservice.dto.TrackingDTO;
import com.zgy.handle.tradingservice.mapper.TrackingMapper;
import com.zgy.handle.tradingservice.model.Tracking;
import com.zgy.handle.tradingservice.service.TrackingQueryService;
import com.zgy.handle.tradingservice.service.TrackingUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: a4423
 * @date: 2021/4/9 22:44
 */
@RestController
@RequestMapping(value = "tracking/query")
public class TrackingQueryController extends BaseQueryController<Tracking, TrackingDTO> {
    @Autowired
    private TrackingMapper trackingMapper;
    private TrackingQueryService trackingQueryService;
    private TrackingUpdateService trackingUpdateService;

    public TrackingQueryController(TrackingUpdateService trackingUpdateService, TrackingQueryService trackingQueryService) {
        super(trackingUpdateService, trackingQueryService);
        this.trackingQueryService = trackingQueryService;
        this.trackingUpdateService = trackingUpdateService;
    }

    /**
     * 将实体列表转化为id和text列表
     *
     * @param trackingList
     * @return
     */
    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Tracking> trackingList) {
        return null;
    }

    /**
     * 实体列表转化为dto列表
     *
     * @param trackingList 实体列表
     * @return dto列表
     */
    @Override
    public List<TrackingDTO> convertTtoU(List<Tracking> trackingList) {
        return trackingMapper.toTrackingDTOList(trackingList);
    }

    /**
     * 将实体对象转为dto
     *
     * @param tracking
     * @return
     */
    @Override
    public TrackingDTO convertTtoU(Tracking tracking) {
        return trackingMapper.toTrackingDTO(tracking);
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
