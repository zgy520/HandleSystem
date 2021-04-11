package com.zgy.handle.tradingservice.mapper;

import com.zgy.handle.tradingservice.dto.TradingPlanDTO;
import com.zgy.handle.tradingservice.model.TradingPlan;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author: a4423
 * @date: 2021/4/9 22:32
 */
@Mapper(componentModel = "spring")
public interface TradingPlanMapper {
    TradingPlan toTradingPlan(TradingPlanDTO tradingPlanDTO);
    TradingPlanDTO toTradingPlanDTO(TradingPlan tradingPlan);
    List<TradingPlanDTO> toTradingPlanDTOList(List<TradingPlan> tradingPlanList);
}
