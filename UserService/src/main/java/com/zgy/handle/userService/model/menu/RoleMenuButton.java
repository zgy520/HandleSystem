package com.zgy.handle.userService.model.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.authority.role.Role;

import javax.persistence.*;

@Entity
@Table(name = "system_role_menuu_button")
public class RoleMenuButton {
    @EmbeddedId
    private RoleMenuButtonPK id;
    @ManyToOne
    @JoinColumn(name = "roleId")
    @MapsId("roleId")
    @JsonIgnore
    private Role role;
    @ManyToOne
    @JoinColumn(name = "menuId")
    @MapsId("menuId")
    @JsonIgnore
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "buttonId")
    @MapsId("buttonId")
    @JsonIgnore
    private Button button;

}
