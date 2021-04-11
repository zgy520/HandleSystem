package com.zgy.handle.tradingservice.repository;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.tradingservice.model.Change;
import org.springframework.stereotype.Repository;

/**
 * @author: a4423
 * @date: 2021/4/7 23:07
 */
@Repository
public interface ChangeQueryRepository extends QueryRepository<Change> {
}
