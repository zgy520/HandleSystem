package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.tradingservice.dto.TrackingDTO;
import com.zgy.handle.tradingservice.model.Tracking;
import com.zgy.handle.tradingservice.model.Tracking_;
import com.zgy.handle.tradingservice.repository.TrackingQueryRepository;
import com.zgy.handle.tradingservice.service.TrackingQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2021/4/7 23:10
 */
@Service
@Slf4j
public class TrackingQueryServiceImpl extends BaseQueryServiceImpl<Tracking, TrackingDTO> implements TrackingQueryService {
    private TrackingQueryRepository trackingQueryRepository;
    public TrackingQueryServiceImpl(TrackingQueryRepository trackingQueryRepository) {
        super(trackingQueryRepository);
        this.trackingQueryRepository = trackingQueryRepository;
    }

    /**
     * 实现业务数据的查询
     *
     * @param dto
     * @return
     */
    @Override
    public Specification<Tracking> querySpecification(TrackingDTO dto) {
        Specification<Tracking> specification = Specification
                .where(dto.getPlanId() == null ? null : trackingQueryRepository.fieldEquals(Tracking_.PLAN_ID, dto.getPlanId().toString()));
        return specification;
    }

    /**
     * 根据planId计算总的收益
     *
     * @param planId
     * @return
     */
    @Override
    public Double sumByPlanId(Long planId) {
        return trackingQueryRepository.sumByPlanId(planId);
    }

    /**
     * 根据计划id获取已提取的金额
     *
     * @param planId
     * @return
     */
    @Override
    public Double sumExtractMoneyByPlanId(Long planId) {
        return trackingQueryRepository.sumExtractByPlanId(planId);
    }
}
