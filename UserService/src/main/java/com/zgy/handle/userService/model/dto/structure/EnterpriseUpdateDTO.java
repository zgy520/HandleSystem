package com.zgy.handle.userService.model.dto.structure;

import com.zgy.handle.userService.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseUpdateDTO extends BaseDTO {
    private String code;
    private String name;
    private String shortName;
    private String phonePerson; // 联系人
    private String phoneNumber; // 联系电话
    private String phoneEmail; // 联系方式
    private String registerPerson; // 注册人
    private String identity; // 社会统一信用号
    private Date initorDate; // 成立日期
    private String address;
    private String note;
    private String parentId;
    private String industryId;
}
