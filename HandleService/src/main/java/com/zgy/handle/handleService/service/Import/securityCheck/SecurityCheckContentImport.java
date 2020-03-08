package com.zgy.handle.handleService.service.Import.securityCheck;

import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.handleService.excelTools.ExcelImpl;
import org.apache.commons.lang.StringUtils;

public class SecurityCheckContentImport extends ExcelImpl {
    public SecurityCheckContentImport(String excelPath) {
        super(excelPath);
    }

    @Override
    public String[] getExampleRowData() {
        return new String[0];
    }

    @Override
    public String[] getHeaderTitles() {
        String[] headers = {"检查点编号","检查要点","检查组","检查方法及要求"};
        return headers;
    }

    @Override
    public String[] getHeaderFields() {
        String[] fields = {"code","importantPoint","itemGroup","checkContent"};
        return fields;
    }

    @Override
    public boolean judgeTemplateEffective() {
        return isEffectiveTemplate("检查点编号,检查要点",2);
    }

    @Override
    public boolean isSkipHeader() {
        return false;
    }

    @Override
    public boolean validateJsonData(JSONObject jsonObject) {
        boolean flag = true;
        StringBuilder stringBuilder = new StringBuilder();
        boolean isEmptyOfUserCode = jsonObject.containsKey("code") && StringUtils.isNotBlank(jsonObject.getString("code"));
        boolean isEmptyOfDepart = jsonObject.containsKey("importantPoint") && StringUtils.isNotBlank(jsonObject.getString("importantPoint"));
        boolean isEmptyOfFrequency = jsonObject.containsKey("checkContent") && StringUtils.isNotBlank(jsonObject.getString("checkContent"));
        if (!isEmptyOfUserCode){
            flag = false;
            stringBuilder.append("检查点不能为空;");
        }
        if (!isEmptyOfDepart){
            flag = false;
            stringBuilder.append("检查要点不能为空;");
        }
        if (!isEmptyOfFrequency){
            flag = false;
            stringBuilder.append("检查内容不能为空;");
        }
        if (!flag){
            jsonObject.put(FAIL_REASON,stringBuilder.toString());
        }
        return flag;
    }
}
