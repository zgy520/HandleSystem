package com.zgy.handle.stockservice.constant;

import lombok.Data;
import lombok.Getter;

import java.time.LocalTime;

/**
 * 分析的固定时间节点
 * @author: a4423
 * @date: 2021/2/19 22:21
 */
@Data
public class FixTimeConstant {
    /**
     * 竞价时间节点
     */
    @Getter
    private static final LocalTime biddingTime = LocalTime.of(9,29,29);
    /**
     * 开盘节点
     */
    @Getter
    private static final LocalTime openTime = LocalTime.of(9,30,00);

    /**
     * 开盘15分钟的节点
     */
    @Getter
    private static final LocalTime open15Time = LocalTime.of(9,45,00);
    /**
     * 开盘半小时的节点
     */
    @Getter
    private static final LocalTime open30Time = LocalTime.of(10,00,00);
    /**
     * 开盘1小时的节点
     */
    @Getter
    private static final LocalTime open60Time = LocalTime.of(10,30,00);
    /**
     * 上午节点
     */
    @Getter
    private static final LocalTime forenoonTime = LocalTime.of(11,30,00);
    /**
     * 下午开盘15分钟的节点
     */
    @Getter
    private static final LocalTime afterNoon15Time = LocalTime.of(13,15,00);
    /**
     * 下午开盘30分钟的节点
     */
    @Getter
    private static final LocalTime afterNoon30Time = LocalTime.of(13,30,00);
    /**
     * 下午开盘1小时的节点
     */
    @Getter
    private static final LocalTime afterNoon60Time = LocalTime.of(14,00,00);/**
     * 下午开盘半小时的节点
     */
    @Getter
    private static final LocalTime beforeClose60Time = LocalTime.of(14,30,00);
    /**
     * 下午收盘前15分钟节点
     */
    @Getter
    private static final LocalTime beforeClose15Time = LocalTime.of(14,45,00);
    /**
     * 收盘节点
     */
    @Getter
    private static final LocalTime closeTime = LocalTime.of(15,00,00);
}
