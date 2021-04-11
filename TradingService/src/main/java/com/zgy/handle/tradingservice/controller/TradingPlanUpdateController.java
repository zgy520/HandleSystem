package com.zgy.handle.tradingservice.controller;

import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.tradingservice.dto.TradingPlanDTO;
import com.zgy.handle.tradingservice.mapper.TradingPlanMapper;
import com.zgy.handle.tradingservice.model.TradingPlan;
import com.zgy.handle.tradingservice.service.TradingPlanQueryService;
import com.zgy.handle.tradingservice.service.TradingPlanUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: a4423
 * @date: 2021/4/9 22:37
 */
@RestController
@RequestMapping(value = "tradingPlan/update")
public class TradingPlanUpdateController extends BaseUpdateController<TradingPlan, TradingPlanDTO> {
    @Autowired
    private TradingPlanMapper tradingPlanMapper;
    private TradingPlanQueryService tradingPlanQueryService;
    private TradingPlanUpdateService tradingPlanUpdateService;

    public TradingPlanUpdateController(TradingPlanUpdateService tradingPlanUpdateService, TradingPlanQueryService tradingPlanQueryService) {
        super(tradingPlanUpdateService, tradingPlanQueryService);
        this.tradingPlanQueryService = tradingPlanQueryService;
        this.tradingPlanUpdateService = tradingPlanUpdateService;
    }

    /**
     * 将dto转为实体
     *
     * @param tradingPlanDTO
     * @return
     */
    @Override
    public TradingPlan convertUtoT(TradingPlanDTO tradingPlanDTO) {
        return tradingPlanMapper.toTradingPlan(tradingPlanDTO);
    }
}
