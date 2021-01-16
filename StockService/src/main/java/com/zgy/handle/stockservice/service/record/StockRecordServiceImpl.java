package com.zgy.handle.stockservice.service.record;

import cn.hutool.core.bean.BeanUtil;
import com.zgy.handle.stockservice.dao.record.StockRecordRepository;
import com.zgy.handle.stockservice.model.StockDialyTrend;
import com.zgy.handle.stockservice.model.record.StockRecord;
import com.zgy.handle.stockservice.util.SpingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2020/12/26 14:08
 */
@Service
@Slf4j
public class StockRecordServiceImpl implements StockRecordService{
    private StockRecordRepository stockRecordRepository;
    @Autowired
    public StockRecordServiceImpl(StockRecordRepository stockRecordRepository){
        this.stockRecordRepository = stockRecordRepository;
    }


    @Override
    public void addRecord(StockRecord stockRecord){

        StockRecord oldRecord = stockRecordRepository.findByCurDateAndCode(stockRecord.getCurDate(),stockRecord.getCode());
        if (oldRecord == null){
            // 不存在，则新增
            stockRecordRepository.save(stockRecord);
        }else {
            // 已经存在，则将非空数据进行赋值
            BeanUtil.copyProperties(stockRecord, oldRecord, SpingUtils.getNullPropertyNames(stockRecord));
            stockRecordRepository.save(oldRecord);
        }
    }

    @Override
    public StockRecord getDetail(String code, LocalDate recordDate) {
        StockRecord stockRecord = stockRecordRepository.findByCurDateAndCode(recordDate,code);
        return stockRecord;
    }
}
