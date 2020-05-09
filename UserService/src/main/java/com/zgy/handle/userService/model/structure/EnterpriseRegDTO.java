package com.zgy.handle.userService.model.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 企业注册dto
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseRegDTO {
    private String id;
    private String prefix;
    private String name;
    private String uec; // 企业统一代码
    private String industry; // 所属行业
    private String regCatalog; //注册类别
    private String frdb; // 法人代表
    private String zjyxq; // 证件有效期
    private String regLocation; // 注册地址
    private String person; // 联系人
    private String phone; // 手机号
    private String fixedPhone; // 固定电话
    private String email; // 邮箱
    private String lxdDepart; // 联系人部门
    private String regDate; // 注册时间
    private String userName; // 用户名
    private String gssr; // 公司上年末收入
    private String fzrq; // 发证日期
    private String ybian; // 邮编
    private String duty; // 职务
    private String changeDate; // 变更时间
    private String parentId;
    private String industryId;
    private String note;
    private String checkStatus; // 审核状态
    private String authorStatus; // 授权状态
    private String checkPerson; // 审核人
    private String checkDate; // 审核时间
    private String province; // 省份

}
