package com.zgy.handle.stockservice.enums;

/**
 * @author: a4423
 * @date: 2020/10/18 23:17
 * 开盘状态
 */
public enum OpeningStatus {
    /**
     * 定义开盘状态
     */
    LOW("低开"),
    HIGH("高开"),
    FLAT("平开");

    private String value;
    OpeningStatus(String value){
        this.value = value;
    }

    /**
     * 根据tag获取对应的值
     * @param tagName LOW,HIGH,FLAT
     * @return 低开，高开，平开
     */
    public static String getValueByTagName(String tagName){
        String value = "";
        for (OpeningStatus openingStatus : OpeningStatus.values()){
            if (tagName.equals(openingStatus.name())){
                value = openingStatus.value;
                break;
            }
        }
        return value;
    }
}
