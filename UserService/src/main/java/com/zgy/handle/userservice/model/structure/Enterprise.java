package com.zgy.handle.userservice.model.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
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
@Audited
@Table(name = "system_enterprise")
public class Enterprise extends BaseModel implements Serializable {
    private String code;
    private String name;
    private String shortName; // 简称
    private String phonePerson; // 联系人
    private String phoneNumber; // 联系电话
    private String phoneEmail; // 联系方式
    private String registerPerson; // 注册人
    private String identity; // 社会统一信用号
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date initorDate; // 成立日期
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paretnId",referencedColumnName = "id")
    @JsonIgnore
    private Enterprise parent; // 上级企业
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "industryId",referencedColumnName = "id")
    @JsonIgnore
    private Industry industry; // 所属行业
}
