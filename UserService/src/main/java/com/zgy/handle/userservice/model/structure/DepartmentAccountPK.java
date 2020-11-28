package com.zgy.handle.userservice.model.structure;

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
public class DepartmentAccountPK implements Serializable {
    private Long departId;
    private Long accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DepartmentAccountPK that = (DepartmentAccountPK) o;
        return Objects.equals(departId, that.departId) &&
                Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departId, accountId);
    }
}
