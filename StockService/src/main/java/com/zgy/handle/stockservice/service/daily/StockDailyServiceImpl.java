package com.zgy.handle.stockservice.service.daily;

import com.zgy.handle.stockservice.dao.StockDailyRepository;
import com.zgy.handle.stockservice.model.StockDaily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: a4423
 * @date: 2021/2/19 23:09
 */
@Service
public class StockDailyServiceImpl implements StockDailyService{
    private StockDailyRepository stockDailyRepository;
    @Autowired
    public StockDailyServiceImpl(StockDailyRepository stockDailyRepository){
        this.stockDailyRepository = stockDailyRepository;
    }
    /**
     * 更新前一交易日的价格
     */
    @Override
    public void updatePreClosePrice() {
        List<StockDaily> updateList = new ArrayList<>();
        List<StockDaily> list = stockDailyRepository.findAllByCode("601100")
                .stream().sorted(Comparator.comparing(StockDaily::getCurDate))
                .collect(Collectors.toList());
        Double firstPrice = list.get(0).getEndPrice();
        for (int i = 1; i < list.size(); i++){
            StockDaily cur = list.get(i);
            cur.setPreClosePrice(firstPrice);
            updateList.add(cur);
            firstPrice = cur.getEndPrice();
        }
        stockDailyRepository.saveAll(updateList);
    }
}
