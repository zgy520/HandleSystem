package com.zgy.handle.userservice.model.menu;

import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author a4423
 */
@Entity
@Table(name = "system_role_menu")
@Audited
@Data
public class RoleMenu {
    @EmbeddedId
    private RoleMenuPK roleMenuPK;

    /*@ManyToOne
    @JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @MapsId("roleId")
    @JsonIgnore
    private Role role;
    @ManyToOne
    @JoinColumn(name = "menuId", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @MapsId("menuId")
    @JsonIgnore
    private Menu menu;*/
}
