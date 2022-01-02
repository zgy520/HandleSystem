package com.zgy.handle.tradingservice.repository;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.tradingservice.model.Tracking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author: a4423
 * @date: 2021/4/7 23:07
 */
@Repository
public interface TrackingQueryRepository extends QueryRepository<Tracking> {
    /**
     * 根据planId计算总的收益
     * @param planId
     * @return
     */
    @Query(nativeQuery = true, value = "select sum(priceDiff) from trading_track where planId = :planId")
    Double sumByPlanId(Long planId);
}
