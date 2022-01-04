package com.zgy.handle.tradingservice.repository;

import com.zgy.handle.tradingservice.model.CostPrice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: a4423
 * @date: 2022/1/4 21:50
 */
public interface CostPriceRepository extends JpaRepository<CostPrice,Long> {
}
