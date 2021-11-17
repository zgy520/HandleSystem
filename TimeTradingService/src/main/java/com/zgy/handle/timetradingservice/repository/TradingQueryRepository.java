package com.zgy.handle.timetradingservice.repository;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.timetradingservice.model.StockDailyDetail;
import org.springframework.stereotype.Repository;

/**
 * @author: a4423
 * @date: 2021/4/14 22:39
 */
@Repository
public interface TradingQueryRepository extends QueryRepository<StockDailyDetail> {
}
