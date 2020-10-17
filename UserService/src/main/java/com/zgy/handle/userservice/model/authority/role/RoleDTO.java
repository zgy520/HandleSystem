package com.zgy.handle.userservice.model.authority.role;

import com.zgy.excel.export.annotation.ExcelExport;
import com.zgy.excel.export.annotation.ExcelProperty;
import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.Data;

/**
 * @author a4423
 */
@Data
@ExcelExport(title = "系统角色列表",fileName = "角色",sheetName = "角色列表")
public class RoleDTO extends BaseDTO {
    @ExcelProperty(title = "角色编码",serial = 1)
    private String code;
    @ExcelProperty(title = "角色名称",serial = 2)
    private String name;
    private String note;
    //private List<String> userList = new ArrayList<>();
}
