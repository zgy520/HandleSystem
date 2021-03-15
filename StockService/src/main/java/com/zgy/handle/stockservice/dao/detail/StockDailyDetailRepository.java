package com.zgy.handle.stockservice.dao.detail;

import com.zgy.handle.stockservice.dto.detail.test.StockDailyBase;
import com.zgy.handle.stockservice.dto.detail.test.StockDailyInterfaceBase;
import com.zgy.handle.stockservice.model.detail.StockDailyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * 成交明细
 *
 * @author: a4423
 * @date: 2021/1/2 10:31
 */
@Repository
public interface StockDailyDetailRepository extends JpaRepository<StockDailyDetail, Long> {
    /**
     * 根据代码和日期获取明细信息
     *
     * @param code    代码
     * @param curDate 日期
     * @return
     */
    List<StockDailyDetail> findByCodeAndCurDate(String code, LocalDate curDate);

    /**
     * 谋取某一交易时刻之前的数据
     *
     * @param code      代码
     * @param localDate 日期
     * @param localTime 时间
     * @return
     */
    List<StockDailyDetail> findByCodeAndCurDateAndCurTimeLessThan(String code, LocalDate localDate, LocalTime localTime);

    /**
     * 获取在某个时间之后的交易详情
     *
     * @param code    代码
     * @param curDate 日期
     * @return
     */
    List<StockDailyDetail> findByCodeAndCurDateGreaterThanEqual(String code, LocalDate curDate);


    /**
     * 根据代码获取所有的交易日
     *
     * @param code 股票代码
     * @return
     */
    @Query(nativeQuery = true, value = "select distinct curDate from stock_daily_detail where code = :code order by curDate desc")
    List<Date> findAllCurDateByCode(String code);

    @Transactional(readOnly = true)
    @Query("select new com.zgy.handle.stockservice.dto.detail.test.StockDailyBase(name,code,curDate,curTime,price,tradingCount,type,result) from StockDailyDetail where code = :code")
    List<StockDailyBase> findByXCode(@Param("code") String code);

    /**
     * 根据日期和代码获取前15分钟的成交量
     *
     * @param code    代码
     * @param curDate 日期
     * @return
     */
    @Transactional(readOnly = true)
    @Query("select new com.zgy.handle.stockservice.dto.detail.test.StockDailyBase(name,code,curDate,curTime,price,tradingCount,type,result) from StockDailyDetail where code = :code and curDate = :curDate and curTime between :startTime and :endTime")
    List<StockDailyBase> getByCodeAndCurDateAndBetweenCurTime(@Param("code") String code, @Param("curDate") LocalDate curDate, @Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);

    @Transactional(readOnly = true)
    @Query("select new com.zgy.handle.stockservice.dto.detail.test.StockDailyBase(name,code,curDate,curTime,price,tradingCount,type,result) from StockDailyDetail where code = :code and curDate = :curDate")
    List<StockDailyBase> getByCodeAndCurDate(@Param("code") String code, @Param("curDate") LocalDate curDate);

    List<StockDailyInterfaceBase> findByCode(String code);

    List<StockDailyDetail> findAllByCode(String code);
}
