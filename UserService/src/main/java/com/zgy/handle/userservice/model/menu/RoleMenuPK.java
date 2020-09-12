package com.zgy.handle.userservice.model.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author a4423
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuPK implements Serializable {
    private Long roleId;
    private Long menuId;
}
