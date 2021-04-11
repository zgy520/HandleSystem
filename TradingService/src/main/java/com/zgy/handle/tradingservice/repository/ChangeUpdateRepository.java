package com.zgy.handle.tradingservice.repository;

import com.zgy.handle.common.repository.base.UpdateRepository;
import com.zgy.handle.tradingservice.model.Change;
import org.springframework.stereotype.Repository;

/**
 * @author: a4423
 * @date: 2021/4/8 23:02
 */
@Repository
public interface ChangeUpdateRepository extends UpdateRepository<Change> {
}
