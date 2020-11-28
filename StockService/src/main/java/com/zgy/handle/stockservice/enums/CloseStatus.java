package com.zgy.handle.stockservice.enums;

/**
 * @author: a4423
 * @date: 2020/10/18 23:28
 * 尾盘状态
 */
public enum CloseStatus {
    /**
     * 定义尾盘状态
     */
    UP("拉升"),
    DOWN("打压"),
    FLAT("持平"),
    BIG_UP("大幅拉升"),
    BIG_DOWN("大幅打压");

    private String value;

    CloseStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
