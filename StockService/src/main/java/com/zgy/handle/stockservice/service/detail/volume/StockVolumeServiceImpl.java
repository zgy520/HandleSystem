package com.zgy.handle.stockservice.service.detail.volume;

import com.zgy.handle.stockservice.constant.FixTimeConstant;
import com.zgy.handle.stockservice.dao.detail.StockDailyDetailRepository;
import com.zgy.handle.stockservice.dto.detail.test.StockDailyBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: a4423
 * @date: 2021/3/1 23:04
 */
@Service
@Slf4j
public class StockVolumeServiceImpl implements StockVolumeService {

    @Autowired
    private StockDailyDetailRepository stockDailyDetailRepository;

    /**
     * 前15分钟的成交量
     *
     * @param curDate 日期
     * @param code    代码
     */
    @Override
    public void pre15Volume(LocalDate curDate, String code) {
        List<StockDailyBase> list = stockDailyDetailRepository.getByCodeAndCurDate(code,curDate);
        calVolume("前十五分钟",curDate,code,FixTimeConstant.getOpenTime(),FixTimeConstant.getOpen15Time(),list);
        calVolume("前三十分钟",curDate,code,FixTimeConstant.getOpenTime(),FixTimeConstant.getOpen30Time(),list);
        calVolume("前六十分钟",curDate,code,FixTimeConstant.getOpenTime(),FixTimeConstant.getOpen60Time(),list);
        calVolume("上午情况",curDate,code,FixTimeConstant.getOpenTime(),FixTimeConstant.getForenoonTime(),list);
        calVolume("下午前十五分钟",curDate,code,FixTimeConstant.getForenoonTime(),FixTimeConstant.getAfterNoon15Time(),list);
        calVolume("下午前30分钟",curDate,code,FixTimeConstant.getForenoonTime(),FixTimeConstant.getAfterNoon30Time(),list);
        calVolume("上午前60分钟",curDate,code,FixTimeConstant.getForenoonTime(),FixTimeConstant.getAfterNoon60Time(),list);
        calVolume("收盘前30分钟",curDate,code,FixTimeConstant.getBeforeClose60Time(),FixTimeConstant.getCloseTime(),list);
        calVolume("收盘前15分钟",curDate,code,FixTimeConstant.getBeforeClose15Time(),FixTimeConstant.getCloseTime(),list);
    }

    private void calVolume(String type, LocalDate curDate, String code,LocalTime startTime, LocalTime endTime,List<StockDailyBase> list){
//        List<StockDailyBase> list = stockDailyDetailRepository.getByCodeAndCurDateAndBetweenCurTime(code, curDate, startTime, endTime);
        list = list.stream().filter(daily -> daily.getCurTime().isAfter(startTime) && daily.getCurTime().isBefore(endTime))
                .collect(Collectors.toList());
        List<StockDailyBase> buyList = list.stream().filter(daily -> daily.getType() == 1).collect(Collectors.toList());
        List<StockDailyBase> sellList = list.stream().filter(daily -> daily.getType() == -1).collect(Collectors.toList());
        BigDecimal buyMoney = sum(buyList,1);
        BigDecimal sellMoney = sum(sellList,-1);
        log.info("\n类型为:" + type +
                "\n买入次数为:" + buyList.size() + ",买入金额为:" + buyMoney +
                "\n,卖出次数为:" + sellList.size() + ",卖出金额为:" + sellMoney +
                "\n,成交次数为:" + (buyList.size() + sellList.size()) + ",成交额为:" + buyMoney.add(sellMoney) +
                "\n,净买入次数:" + (buyList.size() - sellList.size()) + ",净流入：" + buyMoney.subtract(sellMoney)
        );
    }

    /**
     * 计算总金额
     *
     * @param detailList
     * @return
     */
    private BigDecimal sum(List<StockDailyBase> detailList, Integer type) {
        detailList = detailList.stream().filter(detail -> detail.getType().equals(type)).collect(Collectors.toList());
        BigDecimal sum = BigDecimal.ZERO;
        for (StockDailyBase stockDailyDetail : detailList) {
            sum = sum.add(new BigDecimal(stockDailyDetail.getPrice() * stockDailyDetail.getTradingCount() * 100));
        }
        return sum.divide(new BigDecimal(100000000)).setScale(2, RoundingMode.HALF_UP);
    }
}
