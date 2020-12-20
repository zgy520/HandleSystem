package com.zgy.handle.stockservice.dao;

import com.zgy.handle.stockservice.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: a4423
 * @date: 2020/12/20 18:27
 */
@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock findByCode(String code);
}
