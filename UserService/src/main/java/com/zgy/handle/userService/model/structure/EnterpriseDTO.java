package com.zgy.handle.userService.model.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseDTO {
    private String ip;
    private String prefix;
    private String id;
    private String code;
    private String name;
    private String shortName; // 简称
    private String type; // 企业节点列别，二级节点和三级节点
    private String province; // 省份
    private String regCatalog; //注册类别
    private String regCatalogType; // 注册类别类型
    private String person; // 联系人
    private String phone; // 手机号
    private String email; // 邮箱
    private String industry; // 所属行业
    private String uec; // 企业统一代码
    private String uecDate; // 企业统一代码证数得有效期
    private String checkStatus; // 审核状态
    private String authorStatus; // 授权状态
    private String preGQStatus; // 前缀过期状态
    private String prePauseStatus; // 前缀暂停状态
    private String checkPerson; // 审核人
    private String checkDate; // 审核时间
    private String preEffectiveDate; // 前缀有效期
    private String regDate; // 注册时间
    private String note;
    private String parentId;
    private String industryId;

    private String userName; // 用户名

    private String frdb; // 法人代表
    private String zjyxq; // 证件有效期
    private String regLocation; // 注册地址
    private String gssr; // 公司上年末收入
    private String fzrq; // 发证日期
    private String ybian; // 邮编

    private String duty; // 职务
    private String changeDate; // 变更时间
    private String fixedPhone; // 固定电话

    private String authorDate; // 授权时间
    private String prefixDate; // 注册时间
}
