package com.zgy.handle.stockservice.dao;

import com.zgy.handle.stockservice.model.StockDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: a4423
 * @date: 2020/12/20 13:01
 */
@Repository
public interface StockDailyRepository extends JpaRepository<StockDaily,Long> {
    /**
     * 根据代码获取所有的信息
     * @param code
     * @return
     */
    List<StockDaily> findAllByCode(String code);

    /**
     *
     * @param code
     * @param startDate
     * @param endDate
     * @return
     */
    List<StockDaily> findAllByCodeAndCurDateBetween(String code,LocalDate startDate,LocalDate endDate);

    /**
     * 获取某个时间段之后的某个股票的所有信息
     * @param code
     * @param date
     * @return
     */
    List<StockDaily> findAllByCodeAndCurDateAfter(String code, LocalDate date);
}
