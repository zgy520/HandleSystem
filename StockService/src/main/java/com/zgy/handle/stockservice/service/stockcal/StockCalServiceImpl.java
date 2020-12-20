package com.zgy.handle.stockservice.service.stockcal;

import com.zgy.handle.stockservice.dao.StockDailyRepository;
import com.zgy.handle.stockservice.dao.StockRepository;
import com.zgy.handle.stockservice.dto.cal.StockWeekDTO;
import com.zgy.handle.stockservice.dto.cal.SummaryWeekDTO;
import com.zgy.handle.stockservice.model.Stock;
import com.zgy.handle.stockservice.model.StockDaily;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * @author: a4423
 * @date: 2020/12/20 16:57
 */
@Service
@Slf4j
public class StockCalServiceImpl implements StockCalService {
    private StockDailyRepository stockDailyRepository;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    public StockCalServiceImpl(StockDailyRepository stockDailyRepository) {
        this.stockDailyRepository = stockDailyRepository;
    }

    @Override
    public SummaryWeekDTO calDayInfo(LocalDate startDate, LocalDate endDate, String code) {
        SummaryWeekDTO summaryWeekDTO = new SummaryWeekDTO();
        Stock stock = stockRepository.findByCode(code);
        summaryWeekDTO.setCode(code);
        summaryWeekDTO.setName(stock.getName());
        DecimalFormat df = new DecimalFormat("#.00");
        if (startDate == null) {
            startDate = LocalDate.of(2019, 1, 1);
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        List<StockDaily> stockDailyList = stockDailyRepository.findAllByCodeAndCurDateBetween(code, startDate, endDate);
        summaryWeekDTO.setTotalSize(stockDailyList.size());
        long upSize = stockDailyList.stream().filter(stockDaily -> stockDaily.getZdf() > 0).count();
        long downSize = stockDailyList.stream().filter(stockDaily -> stockDaily.getZdf() < 0).count();
        summaryWeekDTO.setUpSize(upSize);
        summaryWeekDTO.setDownSize(downSize);
        summaryWeekDTO.setUpRate(df.format((double) upSize / summaryWeekDTO.getTotalSize() * 100));
        summaryWeekDTO.setDownRate(df.format((double) downSize / summaryWeekDTO.getTotalSize() * 100));
        summaryWeekDTO.setAverage(stockDailyList.stream().mapToDouble(StockDaily::getZdf).average().getAsDouble());


        List<StockWeekDTO> stockWeekDTOList = new ArrayList<>();
        Map<DayOfWeek, List<StockDaily>> weekMap = new HashMap<>();
        for (StockDaily stockDaily : stockDailyList) {
            DayOfWeek dayOfWeek = stockDaily.getCurDate().getDayOfWeek();
            if (!weekMap.containsKey(dayOfWeek)) {
                List<StockDaily> list = new ArrayList<>();
                list.add(stockDaily);
                weekMap.put(dayOfWeek, list);
            } else {
                weekMap.get(dayOfWeek).add(stockDaily);
            }
        }
        weekMap.entrySet().forEach(item -> {
            StockWeekDTO stockWeekDTO = new StockWeekDTO();
            DayOfWeek dayOfWeek = item.getKey();
            List<StockDaily> list = item.getValue();
            long upWeekSize = list.stream().filter(stockDaily -> stockDaily.getZdf() > 0).count();
            long downWeekSize = list.stream().filter(stockDaily -> stockDaily.getZdf() < 0).count();
            Double average =formatDouble2(list.stream().mapToDouble(StockDaily::getZdf).average().getAsDouble());
            double upRate = (double) upWeekSize / list.size() * 100;
            double downRate = (double) downWeekSize / list.size() * 100;
            /*log.info("当前是:" + dayOfWeek.name());
            log.info("total:" + list.size() + ",upSize:" + upSize + ",downSize:" + downSize + ",average:" + average);
            log.info("upRate:" + df.format(upRate) + ",downRate:" + df.format(downRate));*/
            stockWeekDTO.setDayOfWeeek(dayOfWeek.name());
            stockWeekDTO.setTotal(list.size());
            stockWeekDTO.setAverage(average);
            stockWeekDTO.setUpSize(upWeekSize);
            stockWeekDTO.setDownSize(downWeekSize);
            stockWeekDTO.setUpRate(df.format(upRate));
            stockWeekDTO.setDownRate(df.format(downRate));
            stockWeekDTOList.add(stockWeekDTO);
        });
        summaryWeekDTO.setStockWeekDTOList(stockWeekDTOList.stream().sorted(Comparator.comparing(StockWeekDTO::getAverage).reversed()).collect(Collectors.toList()));
//        log.info("average:" + stockDailyList.stream().mapToDouble(StockDaily::getZdf).average().getAsDouble());
        return summaryWeekDTO;
    }

    private static double formatDouble2(double d) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);


        return bg.doubleValue();
    }
}
