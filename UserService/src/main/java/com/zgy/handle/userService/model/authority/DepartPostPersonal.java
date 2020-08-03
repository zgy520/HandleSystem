package com.zgy.handle.userService.model.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.user.Account;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "system_depart_post_personal")
@Data
@Slf4j
public class DepartPostPersonal {
    @EmbeddedId
    private DepartPostfPersonalPK id;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "postId")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "accountId")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @MapsId("departId")
    @JoinColumn(name = "departId")
    @JsonIgnore
    private Department department;
}
