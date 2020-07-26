package com.zgy.handle.userService.model.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "system_department")
@Data
@Slf4j
@Audited
@SQLDelete(sql = "update system_department set isDeleted = true where id = ?")
@Where(clause = BaseModel.SOFT_DELETED_CLAUSE)
public class Department extends BaseModel {
    private String code;
    private String name;
    private String type; // 部门类型
    @Column(name = "sortOrder",columnDefinition = "int default 0")
    private int sortOrder; // 排列顺序
    @ManyToOne
    @JoinColumn(name = "parentId",referencedColumnName = "id")
    @JsonIgnore
    private Department parent;
    @ManyToOne
    @JoinColumn(name = "enterpriseId",referencedColumnName = "id")
    @JsonIgnore
    private Enterprise enterprise; // 所属企业

}
