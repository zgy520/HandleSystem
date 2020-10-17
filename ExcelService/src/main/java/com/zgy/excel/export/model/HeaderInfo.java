package com.zgy.excel.export.model;

import java.util.List;

/**
 * 导出excel的头部
 * @author a4423
 */
public class HeaderInfo {
    // 表头名称
    private String name;
    // 展示的顺序
    private int serial;
    // 子节点
    private List<HeaderInfo> children;


    public HeaderInfo(String name,int serial){
        this.name = name;
        this.serial = serial;
    }

    public void addChild(HeaderInfo headerInfo){
        this.children.add(headerInfo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HeaderInfo> getChildren() {
        return children;
    }

    public void setChildren(List<HeaderInfo> children) {
        this.children = children;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
}
