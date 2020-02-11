package com.zgy.handle.userService.model.structure;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class DepartmentAccountPK implements Serializable {
    private Long departId;
    private Long accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentAccountPK that = (DepartmentAccountPK) o;
        return Objects.equals(departId, that.departId) &&
                Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departId, accountId);
    }
}
