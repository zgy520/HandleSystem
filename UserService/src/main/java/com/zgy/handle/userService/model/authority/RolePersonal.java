package com.zgy.handle.userService.model.authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.BaseModel;
import com.zgy.handle.userService.model.user.Account;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "system_role_personal")
@Data
@Slf4j
public class RolePersonal {
    @EmbeddedId
    private RolePersonalPK id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "roleId")
    @JsonIgnore
    private Role role;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "accountId")
    @JsonIgnore
    private Account account;
}
