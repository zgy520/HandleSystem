package com.zgy.handle.stockservice.enums;

/**
 * 变更状态
 * @author: a4423
 * @date: 2020/12/20 16:21
 */
public enum ChangeStatus {
    UP("上升"),
    DOWN("下降"),
    SHOCK("震荡"),
    UP_DOWN("升转降"),
    DOWN_UP("降转升"),
    SHOCK_UP("震转升"),
    SHOCK_DOWN("震转降");
    private String value;
    ChangeStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
