package com.zgy.handle.userservice.model.user;

/**
 * 卡状态
 * @author: a4423
 * @date: 2020/9/24 21:32
 */
public enum CardStatusEnum {
    ACTIVE("有效"),
    LOSS("挂失"),
    DISABLE("禁用");
    private String value;
    CardStatusEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
