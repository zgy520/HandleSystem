package com.zgy.handle.stockservice.controller;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.stockservice.dto.cal.SummaryWeekDTO;
import com.zgy.handle.stockservice.service.stockcal.StockCalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2020/12/20 17:03
 */
@RestController
@RequestMapping(value = "stock/cal")
public class StockCalController {
    private StockCalService stockCalService;
    @Autowired
    private StockCalController(StockCalService stockCalService){
        this.stockCalService = stockCalService;
    }

    @GetMapping(value = "weekOfDay")
    public ResponseCode<SummaryWeekDTO> calWeekOfDay(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate, String code){
        if (StringUtils.isBlank(code)){
            code = "601100";
        }
       SummaryWeekDTO summaryWeekDTO = stockCalService.calDayInfo(startDate, endDate,code);
        ResponseCode<SummaryWeekDTO> responseCode = ResponseCode.sucess();
        responseCode.setData(summaryWeekDTO);
        return responseCode;
    }
}
