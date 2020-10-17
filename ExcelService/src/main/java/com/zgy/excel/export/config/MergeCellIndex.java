package com.zgy.excel.export.config;

/**
 * @author a4423
 */
public class MergeCellIndex {
    private int firstRowIndex;
    private int lastRowIndex;
    private int firstColIndex;
    private int lastColIndex;

    public MergeCellIndex(int firstRowIndex,int lastRowIndex,int firstColIndex,int lastColIndex){
        this.firstRowIndex = firstRowIndex;
        this.lastRowIndex = lastRowIndex;
        this.firstColIndex = firstColIndex;
        this.lastColIndex = lastColIndex;
    }

    public int getFirstRowIndex() {
        return firstRowIndex;
    }

    public void setFirstRowIndex(int firstRowIndex) {
        this.firstRowIndex = firstRowIndex;
    }

    public int getLastRowIndex() {
        return lastRowIndex;
    }

    public void setLastRowIndex(int lastRowIndex) {
        this.lastRowIndex = lastRowIndex;
    }

    public int getFirstColIndex() {
        return firstColIndex;
    }

    public void setFirstColIndex(int firstColIndex) {
        this.firstColIndex = firstColIndex;
    }

    public int getLastColIndex() {
        return lastColIndex;
    }

    public void setLastColIndex(int lastColIndex) {
        this.lastColIndex = lastColIndex;
    }
}
