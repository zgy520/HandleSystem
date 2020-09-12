package com.zgy.handle.userservice.model.authority;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class DepartPostfPersonalPK implements Serializable {
    private Long postId;
    private Long accountId;
    private Long departId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DepartPostfPersonalPK that = (DepartPostfPersonalPK) o;
        return Objects.equals(postId, that.postId) &&
                Objects.equals(accountId, that.accountId) &&
                Objects.equals(departId, that.departId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, accountId, departId);
    }
}
