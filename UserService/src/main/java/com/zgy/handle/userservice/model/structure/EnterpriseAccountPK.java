package com.zgy.handle.userservice.model.structure;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author a4423
 */
@Embeddable
@Data
public class EnterpriseAccountPK implements Serializable {
    private Long enterpriseId;
    private Long accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EnterpriseAccountPK that = (EnterpriseAccountPK) o;
        return Objects.equals(enterpriseId, that.enterpriseId) &&
                Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enterpriseId, accountId);
    }
}
