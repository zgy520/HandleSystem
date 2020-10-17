package com.zgy.excel.export.config;

import com.zgy.excel.export.model.HeaderConfig;
import lombok.AccessLevel;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * excel的导出的配置类
 * 以下是配置项
 */
public class ExcelExportConfig<T> {
    // 导出的excel名称
    private String excelFileName;
    // excel工作簿的名称
    private String sheetName = "sheet1";
    // 是否需要表头
    private boolean isShowHeader = false;
    // 是否需要汇总
    private boolean isShowSummary = false;
    // 是否需要查询项
    private boolean isShowQuery = false;
    // 表头是否合并
    private boolean isMergedTitle = false;
    // 合并表头的配置
    private HeaderConfig headerConfig;
    // 是否需要合并行单元格
    private boolean isNeedMergedRows = false;
    // 是否需要换行
    private boolean isSwarpLine = false;
    // 是否需要居中所有的字段
    private boolean isCenterAllFields = false;
    /**
     * 居中字段
     */
    private Set<String> centerFields = new HashSet<>();
    private List<MergeCellIndex> mergeCellIndexList = new ArrayList<>();

    /**
     * 每个字段对应的key
     */
    private String[] fields;
    /**
     * 导出文件的表头
     */
    private String[] titles;
    /**
     * 数据列表
     */
    @Setter(AccessLevel.NONE)
    private List<T> dataSource;

    /**
     * 表头
     */
    private String header = "";

    public ExcelExportConfig(){}

    public ExcelExportConfig(String excelFileName, String sheetName, boolean isShowHeader, String[] titles, String[] fields) {
        this.excelFileName = excelFileName;
        this.isShowHeader = isShowHeader;
        this.titles = titles;
        this.fields = fields;
        if (this.titles != null && this.fields != null && this.titles.length != this.fields.length) {
            try {
                throw new Exception("表头字段值的数量与对应的键的数量不匹配!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotBlank(sheetName)) {
            this.sheetName = sheetName;
        }
    }

    /**
     * 填充数据源
     *
     * @param dataList
     */
    public void fillDataSource(List<T> dataList) {
        this.dataSource = Collections.unmodifiableList(dataList);
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public boolean isShowHeader() {
        return isShowHeader;
    }

    public void setShowHeader(boolean showHeader) {
        isShowHeader = showHeader;
    }

    public boolean isShowSummary() {
        return isShowSummary;
    }

    public void setShowSummary(boolean showSummary) {
        isShowSummary = showSummary;
    }

    public boolean isShowQuery() {
        return isShowQuery;
    }

    public void setShowQuery(boolean showQuery) {
        isShowQuery = showQuery;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public List<T> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<T> dataSource) {
        this.dataSource = dataSource;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public boolean isNeedMergedRows() {
        return isNeedMergedRows;
    }

    public void setNeedMergedRows(boolean needMergedRows) {
        isNeedMergedRows = needMergedRows;
    }

    public List<MergeCellIndex> getMergeCellIndexList() {
        return mergeCellIndexList;
    }

    public void setMergeCellIndexList(List<MergeCellIndex> mergeCellIndexList) {
        this.mergeCellIndexList = mergeCellIndexList;
    }

    public boolean isSwarpLine() {
        return isSwarpLine;
    }

    public void setSwarpLine(boolean swarpLine) {
        isSwarpLine = swarpLine;
    }

    public boolean isCenterAllFields() {
        return isCenterAllFields;
    }

    public void setCenterAllFields(boolean centerAllFields) {
        isCenterAllFields = centerAllFields;
    }

    /**
     * 是否是居中字段
     *
     * @param centerField
     * @return
     */
    public boolean isCenter(String centerField) {
        return this.centerFields.contains(centerField);
    }

    /**
     * 添加居中字段
     *
     * @param centerField
     */
    public void addCenterFields(String centerField) {
        this.centerFields.add(centerField);
    }

    public Set<String> getCenterFields() {
        return centerFields;
    }

    public boolean isMergedTitle() {
        return isMergedTitle;
    }

    public void setMergedTitle(boolean mergedTitle) {
        isMergedTitle = mergedTitle;
    }

    public HeaderConfig getHeaderConfig() {
        return headerConfig;
    }

    public void setHeaderConfig(HeaderConfig headerConfig) {
        this.headerConfig = headerConfig;
    }
}
