package com.zgy.handle.userservice.model.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.user.Account;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "system_depart_post_personal")
@Data
@Slf4j
public class DepartPostPersonal implements Serializable {
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
