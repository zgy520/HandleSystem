package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.tradingservice.dto.CostPriceDTO;
import com.zgy.handle.tradingservice.dto.TradingPlanDTO;
import com.zgy.handle.tradingservice.model.CostPrice;
import com.zgy.handle.tradingservice.model.TradingPlan;
import com.zgy.handle.tradingservice.repository.TradingPlanUpdateRepository;
import com.zgy.handle.tradingservice.service.CostPriceService;
import com.zgy.handle.tradingservice.service.TradingPlanUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author: a4423
 * @date: 2021/4/8 22:59
 */
@Service
@Slf4j
public class TradingPlanUpdateServiceImpl extends BaseUpdateServiceImpl<TradingPlan, TradingPlanDTO> implements TradingPlanUpdateService {
    private TradingPlanUpdateRepository tradingPlanUpdateRepository;
    @Autowired
    private CostPriceService costPriceService;

    public TradingPlanUpdateServiceImpl(TradingPlanUpdateRepository tradingPlanUpdateRepository) {
        super(tradingPlanUpdateRepository);
        this.tradingPlanUpdateRepository = tradingPlanUpdateRepository;
    }

    /**
     * 结束计划
     *
     * @param id
     */
    @Override
    public void endPlan(Long id) {
        Optional<TradingPlan> tradingPlanOptional = tradingPlanUpdateRepository.findById(id);
        if (tradingPlanOptional.isPresent()) {
            TradingPlan tradingPlan = tradingPlanOptional.get();
            tradingPlan.setEndDate(LocalDate.now());
            tradingPlanUpdateRepository.save(tradingPlan);
        }
    }

    /**
     * 更新成本价
     *
     * @param costPriceDTO 成本价信息
     * @return
     */
    @Override
    public TradingPlan updateCostPrice(CostPriceDTO costPriceDTO) {
        Optional<TradingPlan> tradingPlanOptional = tradingPlanUpdateRepository.findById(Long.valueOf(costPriceDTO.getId()));
        if (tradingPlanOptional.isPresent()) {
            TradingPlan tradingPlan = tradingPlanOptional.get();
            tradingPlan.setCostPrice(costPriceDTO.getCostPrice());
            tradingPlanUpdateRepository.save(tradingPlan);

            CostPrice costPrice = new CostPrice();
            costPrice.setCode(tradingPlan.getCode());
            costPrice.setRecordDate(LocalDate.now());
            costPrice.setPrice(costPriceDTO.getCostPrice());
            costPrice.setCount(costPriceDTO.getCount());
            costPriceService.addCostPrice(costPrice);

            return tradingPlan;
        }
        return null;
    }
}
