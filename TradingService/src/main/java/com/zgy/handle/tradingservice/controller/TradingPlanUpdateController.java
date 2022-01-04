package com.zgy.handle.tradingservice.controller;

import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.tradingservice.dto.CostPriceDTO;
import com.zgy.handle.tradingservice.dto.TradingPlanDTO;
import com.zgy.handle.tradingservice.mapper.TradingPlanMapper;
import com.zgy.handle.tradingservice.model.CostPrice;
import com.zgy.handle.tradingservice.model.TradingPlan;
import com.zgy.handle.tradingservice.service.CostPriceService;
import com.zgy.handle.tradingservice.service.TradingPlanQueryService;
import com.zgy.handle.tradingservice.service.TradingPlanUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: a4423
 * @date: 2021/4/9 22:37
 */
@RestController
@RequestMapping(value = "tradingPlan/update")
@Slf4j
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

    @PostMapping(value = "endPlan/{id}")
    private ResponseCode<String> endPlan(@PathVariable(value = "id") Long id) {
        ResponseCode responseCode = ResponseCode.sucess();
        tradingPlanUpdateService.endPlan(id);
        return responseCode;
    }

    @PostMapping(value = "updateCostPrice")
    private ResponseCode<TradingPlan> updateCostPrice(@RequestBody CostPriceDTO costPriceDTO){
        ResponseCode<TradingPlan> responseCode = ResponseCode.sucess();
        log.info(costPriceDTO.toString());
        tradingPlanUpdateService.updateCostPrice(costPriceDTO);


        return responseCode;
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
