package com.zgy.handle.stockservice.service.move;

import com.zgy.handle.stockservice.dao.StockDailyRepository;
import com.zgy.handle.stockservice.dao.StockMovingRepository;
import com.zgy.handle.stockservice.model.StockDaily;
import com.zgy.handle.stockservice.model.StockMoving;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: a4423
 * @date: 2020/12/20 12:59
 */
@Service
@Slf4j
public class StockMovingServiceImpl implements StockMovingService{
    @Autowired
    private StockDailyRepository stockDailyRepository;
    private StockMovingRepository stockMovingRepository;
    @Autowired
    public StockMovingServiceImpl(StockMovingRepository stockMovingRepository){
        this.stockMovingRepository = stockMovingRepository;
    }

    @Override
    public void initStockMoving() {
        List<StockDaily> stockDailyList = stockDailyRepository.findAllByCode("601100")
                .stream().sorted(Comparator.comparing(StockDaily::getCurDate).reversed())
                .collect(Collectors.toList());
        List<StockMoving> stockMovingList = new ArrayList<>();
        for (StockDaily stockDaily : stockDailyList){
            StockMoving stockMoving = new StockMoving();
            stockMoving.setCode(stockDaily.getCode());
            stockMoving.setName(stockDaily.getName());
            stockMoving.setCurDate(stockDaily.getCurDate());
            stockMoving.setPrice(stockDaily.getEndPrice());
            stockMoving.setPrice3(getAverage(stockDailyList,stockDaily,3));
            stockMoving.setPrice5(getAverage(stockDailyList,stockDaily,5));
            stockMoving.setPrice7(getAverage(stockDailyList,stockDaily,7));
            stockMoving.setPrice10(getAverage(stockDailyList,stockDaily,10));
            stockMoving.setPrice15(getAverage(stockDailyList,stockDaily,15));
            stockMoving.setPrice20(getAverage(stockDailyList,stockDaily,20));
            stockMoving.setPrice30(getAverage(stockDailyList,stockDaily,30));
            stockMoving.setPrice45(getAverage(stockDailyList,stockDaily,45));
            stockMoving.setPrice60(getAverage(stockDailyList,stockDaily,60));
            stockMoving.setPrice75(getAverage(stockDailyList,stockDaily,75));
            stockMoving.setPrice100(getAverage(stockDailyList,stockDaily,100));
            stockMovingList.add(stockMoving);
        }
        stockMovingRepository.saveAll(stockMovingList);
    }

    /**
     * 获取对应天数的均线值
     * @param stockDailyList
     * @param stockDaily
     * @param days
     * @return
     */
    private Double getAverage(List<StockDaily> stockDailyList,StockDaily stockDaily, int days){
        Double moviePrice = stockDailyList.stream().filter(stock -> stock.getCurDate().compareTo(stockDaily.getCurDate()) <= 0)
                .limit(days)
                .mapToDouble(StockDaily::getEndPrice).average().getAsDouble();
        return moviePrice;
    }

    @Override
    public void calStockMoving() {

        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        if (!dayOfWeek.name().equals("SATURDAY") && !dayOfWeek.name().equals("SUNDAY")){
            // 计算当天的均线信息
            List<StockDaily> stockDailyList = stockDailyRepository.findAllByCode("601100")
                    .stream().sorted(Comparator.comparing(StockDaily::getCurDate).reversed())
                    .limit(100)
                    .collect(Collectors.toList());
            StockDaily stockDaily = stockDailyList.get(0);

            StockMoving stockMoving = new StockMoving();
            stockMoving.setCode(stockDaily.getCode());
            stockMoving.setName(stockDaily.getName());
            stockMoving.setCurDate(stockDaily.getCurDate());
            stockMoving.setPrice(stockDaily.getEndPrice());
            stockMoving.setPrice3(getAverage(stockDailyList,stockDaily,3));
            stockMoving.setPrice5(getAverage(stockDailyList,stockDaily,5));
            stockMoving.setPrice7(getAverage(stockDailyList,stockDaily,7));
            stockMoving.setPrice10(getAverage(stockDailyList,stockDaily,10));
            stockMoving.setPrice15(getAverage(stockDailyList,stockDaily,15));
            stockMoving.setPrice20(getAverage(stockDailyList,stockDaily,20));
            stockMoving.setPrice30(getAverage(stockDailyList,stockDaily,30));
            stockMoving.setPrice45(getAverage(stockDailyList,stockDaily,45));
            stockMoving.setPrice60(getAverage(stockDailyList,stockDaily,60));
            stockMoving.setPrice75(getAverage(stockDailyList,stockDaily,75));
            stockMoving.setPrice100(getAverage(stockDailyList,stockDaily,100));
            stockMovingRepository.save(stockMoving);
        }else {
            log.info("周末不参与计算");
        }
    }
}
