package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.tradingservice.model.CostPrice;
import com.zgy.handle.tradingservice.repository.CostPriceRepository;
import com.zgy.handle.tradingservice.service.CostPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: a4423
 * @date: 2022/1/4 21:51
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CostPriceServiceImpl implements CostPriceService {
    private final CostPriceRepository costPriceRepository;
    @Override
    public void addCostPrice(CostPrice costPrice) {
        costPriceRepository.save(costPrice);
    }
}
