package com.zgy.handle.tradingservice.repository;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.tradingservice.model.Summary;
import org.springframework.stereotype.Repository;

/**
 * @author: a4423
 * @date: 2021/4/7 23:08
 */
@Repository
public interface SummaryQueryRepository extends QueryRepository<Summary> {
}
