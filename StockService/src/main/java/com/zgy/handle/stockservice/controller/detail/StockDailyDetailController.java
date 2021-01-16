package com.zgy.handle.stockservice.controller.detail;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.stockservice.dto.detail.BaseDetailDTO;
import com.zgy.handle.stockservice.dto.detail.TransactionTypeDTO;
import com.zgy.handle.stockservice.service.detail.StockDailyDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author: a4423
 * @date: 2021/1/2 10:53
 */
@RestController
@RequestMapping(value = "stock/daily/detail")
public class StockDailyDetailController {
    private StockDailyDetailService stockDailyDetailService;

    @Autowired
    public StockDailyDetailController(StockDailyDetailService stockDailyDetailService) {
        this.stockDailyDetailService = stockDailyDetailService;
    }

    @GetMapping(value = "base")
    public ResponseCode<List<BaseDetailDTO>> base(String code, String localDate) {
        ResponseCode<List<BaseDetailDTO>> responseCode = ResponseCode.sucess();
        LocalDate convertDate = LocalDate.now();
        if (StringUtils.isEmpty(code)) {
            code = "601100";
        }
        if (StringUtils.isEmpty(localDate)) {
            convertDate = convertDate.minusDays(5);
        } else {
            convertDate = LocalDate.parse(localDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        responseCode.setData(stockDailyDetailService.base(code, convertDate));
        return responseCode;
    }

    @GetMapping(value = "transactionType")
    public ResponseCode<List<TransactionTypeDTO>> transactionType(String code, String localDate) {
        ResponseCode<List<TransactionTypeDTO>> responseCode = ResponseCode.sucess();
        LocalDate convertDate = LocalDate.now();
        if (StringUtils.isEmpty(code)) {
            code = "601100";
        }
        if (StringUtils.isEmpty(localDate)) {
            convertDate = convertDate.minusDays(5);
        } else {
            convertDate = LocalDate.parse(localDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        responseCode.setData(stockDailyDetailService.transactionInfo(code, convertDate));
        return responseCode;
    }
}
