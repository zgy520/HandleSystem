package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.tradingservice.dto.TradingPlanDTO;
import com.zgy.handle.tradingservice.model.TradingPlan;
import com.zgy.handle.tradingservice.repository.TradingPlanQueryRepository;
import com.zgy.handle.tradingservice.service.TradingPlanQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2021/4/7 23:09
 */
@Service
@Slf4j
public class TradingPlanQueryServiceImpl extends BaseQueryServiceImpl<TradingPlan, TradingPlanDTO> implements TradingPlanQueryService {
    private TradingPlanQueryRepository tradingPlanQueryRepository;
    public TradingPlanQueryServiceImpl(TradingPlanQueryRepository tradingPlanQueryRepository) {
        super(tradingPlanQueryRepository);
        this.tradingPlanQueryRepository = tradingPlanQueryRepository;
    }

    /**
     * 实现业务数据的查询
     *
     * @param dto
     * @return
     */
    @Override
    public Specification<TradingPlan> querySpecification(TradingPlanDTO dto) {
        return null;
    }
}
