package com.zgy.handle.userService.model.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 企业
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "system_enterprise")
public class Enterprise extends BaseModel {
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

    @ManyToOne
    @JoinColumn(name = "paretnId",referencedColumnName = "id",nullable = true)
    @JsonIgnore
    private Enterprise parent; // 上级企业
    @ManyToOne
    @JoinColumn(name = "industryId",referencedColumnName = "id",nullable = true)
    @JsonIgnore
    private Industry belongIndustry; // 所属行业
}
