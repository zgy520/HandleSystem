package com.zgy.handle.userService.model.structure;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class EnterpriseAccountPK implements Serializable {
    private Long enterpriseId;
    private Long accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnterpriseAccountPK that = (EnterpriseAccountPK) o;
        return Objects.equals(enterpriseId, that.enterpriseId) &&
                Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enterpriseId, accountId);
    }
}
