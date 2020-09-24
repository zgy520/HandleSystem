package com.zgy.handle.userservice.model.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author a4423
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuButtonPK implements Serializable {
    private Long roleId;
    private Long menuId;
    private Long buttonId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleMenuButtonPK that = (RoleMenuButtonPK) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(menuId, that.menuId) &&
                Objects.equals(buttonId, that.buttonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, menuId, buttonId);
    }
}
