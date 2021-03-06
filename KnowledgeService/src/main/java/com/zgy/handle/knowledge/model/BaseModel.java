package com.zgy.handle.knowledge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @GeneratedValue(generator = "uuid_short")
    @GenericGenerator(name = "uuid_short",strategy = "com.zgy.handle.common.hibernate.UUIDGenerator")
    @Column(name = "id",nullable = false)
    protected Long id;

    @JsonIgnore
    protected boolean isDeleted = false;
    protected String createdId; // 创建人的id
    protected String orgId; // 创建人所属部门的id
    protected String creator;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    protected Date createTime;
    protected String updator;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    protected Date updateTime;

    @ApiModelProperty("备注")
    @Column(name = "note",columnDefinition = "text null")
    protected String note;
}
