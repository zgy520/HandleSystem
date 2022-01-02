package com.zgy.handle.tradingservice.service;

import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.tradingservice.dto.TrackingDTO;
import com.zgy.handle.tradingservice.model.Tracking;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: a4423
 * @date: 2021/4/7 23:10
 */
public interface TrackingQueryService extends QueryService<Tracking, TrackingDTO> {
    /**
     * 根据planId计算总的收益
     * @param planId
     * @return
     */
    Double sumByPlanId(Long planId);
}
