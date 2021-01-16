package com.zgy.handle.stockservice.dao.record;

import com.zgy.handle.stockservice.model.record.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2020/12/26 14:07
 */
@Repository
public interface StockRecordRepository extends JpaRepository<StockRecord,Long> {
    StockRecord findByCurDateAndCode(LocalDate curDate,String code);
}
