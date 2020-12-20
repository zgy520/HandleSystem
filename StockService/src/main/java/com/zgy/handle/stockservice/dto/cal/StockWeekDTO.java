package com.zgy.handle.stockservice.dto.cal;

import lombok.Data;

/**
 * 股票周统计
 * @author: a4423
 * @date: 2020/12/20 18:18
 */
@Data
public class StockWeekDTO {
    /**
     * 星期几
     */
    private String dayOfWeeek;
    /**
     * 总数量
     */
    private Integer total;
    /**
     * 涨的数量
     */
    private Long upSize;
    /**
     * 跌的数量
     */
    private Long downSize;
    /**
     * 涨跌平均值
     */
    private Double average;
    /**
     * 上涨概率
     */
    private String upRate;
    /**
     * 下跌概率
     */
    private String downRate;

    public String getDayOfWeeek() {
        String convertResult = "";
        switch (dayOfWeeek){
            case "MONDAY":
                convertResult = "星期一";
                break;
            case "TUESDAY":
                convertResult = "星期二";
                break;
            case "WEDNESDAY":
                convertResult = "星期三";
                break;
            case "THURSDAY":
                convertResult="星期四";
                break;
            case "FRIDAY":
                convertResult = "星期五";
                break;
            default:
                break;
        }
        return convertResult;
    }
}
