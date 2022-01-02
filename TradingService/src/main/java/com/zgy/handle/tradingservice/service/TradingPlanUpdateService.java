package com.zgy.handle.tradingservice.service;

import com.zgy.handle.common.service.base.UpdateService;
import com.zgy.handle.tradingservice.dto.CostPriceDTO;
import com.zgy.handle.tradingservice.dto.TradingPlanDTO;
import com.zgy.handle.tradingservice.model.TradingPlan;

/**
 * @author: a4423
 * @date: 2021/4/8 22:54
 */
public interface TradingPlanUpdateService extends UpdateService<TradingPlan, TradingPlanDTO> {
    /**
     * 结束计划
     * @param id
     */
    void endPlan(Long id);

    /**
     * 更新成本价
     * @param costPriceDTO 成本价信息
     * @return
     */
    TradingPlan updateCostPrice(CostPriceDTO costPriceDTO);
}
