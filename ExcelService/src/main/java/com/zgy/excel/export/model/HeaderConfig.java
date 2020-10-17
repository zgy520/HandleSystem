package com.zgy.excel.export.model;

import java.util.List;

/**
 * 表头配置
 * @author a4423
 */
public class HeaderConfig {
    private int mergedCount = 1;
    private List<HeaderInfo> headerInfoList;

    public int getMergedCount() {
        return mergedCount;
    }

    public void setMergedCount(int mergedCount) {
        this.mergedCount = mergedCount;
    }

    public List<HeaderInfo> getHeaderInfoList() {
        return headerInfoList;
    }

    public void setHeaderInfoList(List<HeaderInfo> headerInfoList) {
        this.headerInfoList = headerInfoList;
    }
}
