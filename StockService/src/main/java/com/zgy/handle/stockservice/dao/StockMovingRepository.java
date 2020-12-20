package com.zgy.handle.stockservice.dao;

import com.zgy.handle.stockservice.model.StockMoving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: a4423
 * @date: 2020/12/20 12:59
 */
@Repository
public interface StockMovingRepository extends JpaRepository<StockMoving,Long> {
}
