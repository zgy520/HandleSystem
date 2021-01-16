package com.zgy.handle.stockservice.dao.detail;

import com.zgy.handle.stockservice.model.detail.StockDailyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * 成交明细
 * @author: a4423
 * @date: 2021/1/2 10:31
 */
public interface StockDailyDetailRepository extends JpaRepository<StockDailyDetail,Long> {
    /**
     * 根据代码和日期获取明细信息
     * @param code 代码
     * @param curDate 日期
     * @return
     */
    List<StockDailyDetail> findByCodeAndCurDate(String code, LocalDate curDate);

    /**
     * 获取在某个时间之后的交易详情
     * @param code 代码
     * @param curDate 日期
     * @return
     */
    List<StockDailyDetail> findByCodeAndCurDateGreaterThanEqual(String code,LocalDate curDate);

}
