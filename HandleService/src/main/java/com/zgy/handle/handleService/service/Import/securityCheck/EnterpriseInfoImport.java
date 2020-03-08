package com.zgy.handle.handleService.service.Import.securityCheck;

import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.handleService.excelTools.ExcelImpl;
import org.apache.commons.lang.StringUtils;

public class EnterpriseInfoImport extends ExcelImpl {

    public EnterpriseInfoImport(String excelPath) {
        super(excelPath);
    }

    @Override
    public String[] getHeaderFields() {
        String[] headers = {"name","prefix","regCatalog","regCatalogType","person","phone","email","industry","uec","uecDate","checkStatus","authorStatus","preGQStatus","prePauseStatus","checkPerson","checkDate","preEffectiveDate","regDate","province"};
        return headers;
    }

    @Override
    public boolean judgeTemplateEffective() {
        return isEffectiveTemplate("企业名称:,前缀:",2);
    }

    @Override
    public String[] getExampleRowData() {
        return new String[0];
    }

    @Override
    public String[] getHeaderTitles() {
        String titles[] = {"企业名称:","前缀:","注册类别:","注册类别类型","联系人姓名:","手机号:","邮箱:","所属行业","组织机构代码/统一社会信用代码","组织机构代码/统一社会信用代码证书有效期","审核状态:","授权状态:","前缀过期状态:","前缀暂停状态:","审核人:","审核时间:","前缀有效期:","注册时间:","省份"};
        return titles;
    }

    @Override
    public boolean isSkipHeader() {
        return true;
    }

    @Override
    public boolean validateJsonData(JSONObject jsonObject) {
        boolean flag = true;
        StringBuilder stringBuilder = new StringBuilder();
        boolean isNullOfName = jsonObject.containsKey("name") && StringUtils.isNotBlank(jsonObject.getString("name"));
        if (!isNullOfName){
            flag = false;stringBuilder.append("姓名不能为空");
        }
        if (!flag){
            jsonObject.put(FAIL_REASON,stringBuilder.toString());
        }
        return flag;
    }
}
