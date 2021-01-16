package com.zgy.handle.stockservice.controller.record;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.stockservice.model.record.StockRecord;
import com.zgy.handle.stockservice.service.record.StockRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * 记录
 * @author: a4423
 * @date: 2020/12/26 14:14
 */
@RestController
@RequestMapping(value = "stock/record")
public class StockRecordController {
    private StockRecordService stockRecordService;
    @Autowired
    public StockRecordController(StockRecordService stockRecordService){
        this.stockRecordService = stockRecordService;
    }

    @PostMapping(value = "add")
    public ResponseCode<String> add(StockRecord stockRecord){
        ResponseCode responseCode = ResponseCode.sucess();
        stockRecord.setName("恒立液压");
        stockRecord.setCode("601100");
        stockRecord.setCreator("WX");
        stockRecordService.addRecord(stockRecord);
        return responseCode;
    }

    /**
     * 记录详情
     * @param code 代码
     * @param recordDate 记录日期
     * @return 记录详情
     */
    @GetMapping(value = "detail")
    public ResponseCode<StockRecord> getDetails(String code,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate recordDate){
        if (StringUtils.isEmpty(code)){
            code = "601100";
        }
        ResponseCode<StockRecord> responseCode = ResponseCode.sucess();
        StockRecord stockRecord = stockRecordService.getDetail(code,recordDate);
        responseCode.setData(stockRecord);
        return responseCode;
    }
}
