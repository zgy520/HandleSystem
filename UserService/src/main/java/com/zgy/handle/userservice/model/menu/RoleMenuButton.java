package com.zgy.handle.userservice.model.menu;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author a4423
 */
@Entity
@Table(name = "system_role_menu_button")
@Audited
@Data
public class RoleMenuButton implements Serializable {
    @EmbeddedId
    private RoleMenuButtonPK roleMenuButtonPK;
    /*@ManyToOne
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
    private Button button;*/

}
