package com.zgy.handle.userService.model.authority.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.user.Account;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "system_role_personal")
@Data
@Slf4j
public class RolePersonal implements Serializable {
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
