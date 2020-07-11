package com.zgy.handle.userService.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
@Audited
public class BaseModel {
    public static final String SOFT_DELETED_CLAUSE = "isDeleted = false";

    @Id
    @GeneratedValue(generator = "uuid_short")
    @GenericGenerator(name = "uuid_short",strategy = "com.zgy.handle.userService.model.hibernate.UUIDGenerator")
    @Column(name = "id",nullable = false)
    @JSONField(serializeUsing = ToStringSerializer.class)
    protected Long id;

    @JsonIgnore
    protected boolean isDeleted = false;
    @CreatedBy
    protected String creator;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    protected Date createTime;
    @LastModifiedBy
    protected String updator;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    protected Date updateTime;

    @ApiModelProperty("备注")
    @Column(name = "note",columnDefinition = "text null")
    protected String note;


    private String createdId; // 创建人的id
    private String belongId; // 归属id  部门或者企业

}
