package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.tradingservice.dto.TradingPlanDTO;
import com.zgy.handle.tradingservice.model.TradingPlan;
import com.zgy.handle.tradingservice.repository.TradingPlanUpdateRepository;
import com.zgy.handle.tradingservice.service.TradingPlanUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2021/4/8 22:59
 */
@Service
@Slf4j
public class TradingPlanUpdateServiceImpl extends BaseUpdateServiceImpl<TradingPlan, TradingPlanDTO> implements TradingPlanUpdateService {
    private TradingPlanUpdateRepository tradingPlanUpdateRepository;
    public TradingPlanUpdateServiceImpl(TradingPlanUpdateRepository tradingPlanUpdateRepository) {
        super(tradingPlanUpdateRepository);
        this.tradingPlanUpdateRepository = tradingPlanUpdateRepository;
    }
}
