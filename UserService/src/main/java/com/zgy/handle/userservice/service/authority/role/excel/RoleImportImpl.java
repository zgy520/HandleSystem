package com.zgy.handle.userservice.service.authority.role.excel;

import com.alibaba.fastjson.JSONObject;
import com.zgy.excel.importExcel.ExcelImpl;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: a4423
 * @date: 2020/10/4 15:48
 * 角色的导入实现类
 */
public class RoleImportImpl extends ExcelImpl {
    public RoleImportImpl(String excelPath) {
        super(excelPath);
    }

    @Override
    public String[] getExampleRowData() {
        return new String[0];
    }

    @Override
    public String[] getHeaderTitles() {
        String[] titles = {"角色名称", "编码", "描述"};
        return titles;
    }

    @Override
    public String[] getHeaderFields() {
        String[] fields = {"name", "code", "desc"};
        return fields;
    }

    @Override
    public boolean judgeTemplateEffective() {
        return isEffectiveTemplate("角色名称,编码,描述", 3);
    }

    @Override
    public boolean validateJsonData(JSONObject jsonObject) {
        boolean validateFlag = true;
        boolean isNullOfName = jsonObject.containsKey("name") && StringUtils.isNotBlank(jsonObject.getString("name"));
        boolean isNullOfCode = jsonObject.containsKey("code") && StringUtils.isNotBlank(jsonObject.getString("code"));

        StringBuilder stringBuilder = new StringBuilder();
        if (!isNullOfName) {
            stringBuilder.append("角色名称不能为空;");
            validateFlag = false;
        }
        if (!isNullOfCode) {
            stringBuilder.append("角色编码不能为空;");
            validateFlag = false;
        }
        if (!validateFlag) {
            jsonObject.put(FAIL_REASON, stringBuilder.toString());
        }

        return validateFlag;
    }
}
