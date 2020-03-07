package com.zgy.handle.knowledge.model.common;

/**
 * 资源类型
 */
public enum ResourceType {
    FILE("文件"),
    LINK("链接"),
    SOLUTION("解决方案"),
    CATALOG("目录");

    private String value;
    ResourceType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
