package com.zgy.handle.stockservice.service.util;

import com.zgy.handle.stockservice.dao.detail.StockDailyDetailRepository;
import com.zgy.handle.stockservice.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: a4423
 * @date: 2021/2/17 22:34
 */
@Service
public class StockUtilServiceImpl implements StockUtilService{
    @Autowired
    private StockDailyDetailRepository stockDailyDetailRepository;
    /**
     * 获取所有的交易日,并降序
     *
     * @return
     */
    @Override
    public List<LocalDate> getDealDateAndSortDesc(String code) {
        List<Date> dealDateList = stockDailyDetailRepository.findAllCurDateByCode(code);
        List<LocalDate> result = new ArrayList<>();
        dealDateList.forEach(deal -> {
            result.add(DateUtils.convertDateToLocalDate(deal));
        });
        return result;
    }
}
