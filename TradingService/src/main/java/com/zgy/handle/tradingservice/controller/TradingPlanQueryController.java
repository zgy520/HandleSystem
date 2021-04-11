package com.zgy.handle.tradingservice.controller;

import com.zgy.handle.common.controller.base.BaseQueryController;
import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.tradingservice.dto.TradingPlanDTO;
import com.zgy.handle.tradingservice.mapper.TradingPlanMapper;
import com.zgy.handle.tradingservice.model.TradingPlan;
import com.zgy.handle.tradingservice.service.TradingPlanQueryService;
import com.zgy.handle.tradingservice.service.TradingPlanUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: a4423
 * @date: 2021/4/9 22:26
 */
@RestController
@RequestMapping(value = "tradingPlan/query")
public class TradingPlanQueryController extends BaseQueryController<TradingPlan, TradingPlanDTO> {
    @Autowired
    private TradingPlanMapper tradingPlanMapper;
    private TradingPlanQueryService tradingPlanQueryService;
    private TradingPlanUpdateService tradingPlanUpdateService;
    public TradingPlanQueryController(TradingPlanUpdateService tradingPlanUpdateService, TradingPlanQueryService tradingPlanQueryService) {
        super(tradingPlanUpdateService, tradingPlanQueryService);
        this.tradingPlanUpdateService = tradingPlanUpdateService;
        this.tradingPlanQueryService = tradingPlanQueryService;
    }

    /**
     * 将实体列表转化为id和text列表
     *
     * @param tradingPlans
     * @return
     */
    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<TradingPlan> tradingPlans) {
        return null;
    }

    /**
     * 实体列表转化为dto列表
     *
     * @param tradingPlans 实体列表
     * @return dto列表
     */
    @Override
    public List<TradingPlanDTO> convertTtoU(List<TradingPlan> tradingPlans) {
        return tradingPlanMapper.toTradingPlanDTOList(tradingPlans);
    }

    /**
     * 将实体对象转为dto
     *
     * @param tradingPlan
     * @return
     */
    @Override
    public TradingPlanDTO convertTtoU(TradingPlan tradingPlan) {
        return tradingPlanMapper.toTradingPlanDTO(tradingPlan);
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
