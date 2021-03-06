package com.zgy.handle.userService.model.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.structure.Department;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "system_department_post")
@Data
@Slf4j
public class DepartmentPost {
    @EmbeddedId
    private DepartmentPostPK id;
    @ManyToOne
    @MapsId("departId")
    @JoinColumn(name = "departId")
    @JsonIgnore
    private Department department;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "postId")
    @JsonIgnore
    private Post post;
}
