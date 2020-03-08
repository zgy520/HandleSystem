package com.zgy.handle.handleService.service.Import.securityCheck;

import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.handleService.excelTools.ExcelImpl;

public class MetaNodeImport extends ExcelImpl {

    public MetaNodeImport(String excelPath) {
        super(excelPath);
    }

    @Override
    public String[] getExampleRowData() {
        return new String[0];
    }

    @Override
    public String[] getHeaderTitles() {
        return new String[0];
    }

    @Override
    public String[] getHeaderFields() {
        return new String[0];
    }

    @Override
    public boolean judgeTemplateEffective() {
        return true;
    }

    /**
     * 是否跳过标题
     * false 标识不跳过
     *  true 标识跳过
     * @return
     */
    @Override
    public boolean isSkipHeader() {
        return false;
    }

    @Override
    public boolean validateJsonData(JSONObject jsonObject) {
        return true;
    }
}
