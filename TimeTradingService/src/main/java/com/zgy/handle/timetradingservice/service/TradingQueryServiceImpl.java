package com.zgy.handle.timetradingservice.service;

import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.timetradingservice.model.StockDailyDetail;
import com.zgy.handle.timetradingservice.repository.TradingQueryRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2021/4/14 22:40
 */
@Service
public class TradingQueryServiceImpl extends BaseQueryServiceImpl<StockDailyDetail, StockDailyDetail> implements TradingQueryService {
    private TradingQueryRepository tradingQueryRepository;

    public TradingQueryServiceImpl(TradingQueryRepository tradingQueryRepository) {
        super(tradingQueryRepository);
        this.tradingQueryRepository = tradingQueryRepository;
    }

    /**
     * 实现业务数据的查询
     *
     * @param dto
     * @return
     */
    @Override
    public Specification<StockDailyDetail> querySpecification(StockDailyDetail dto) {
        return null;
    }
}
