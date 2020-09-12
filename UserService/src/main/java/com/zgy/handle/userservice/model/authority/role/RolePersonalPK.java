package com.zgy.handle.userservice.model.authority.role;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class RolePersonalPK implements Serializable {
    private Long roleId;
    private Long accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RolePersonalPK that = (RolePersonalPK) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, accountId);
    }
}
