package com.zgy.handle.userservice.model.authority;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class DepartmentPostPK implements Serializable {
    private Long departId;
    private Long postId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DepartmentPostPK that = (DepartmentPostPK) o;
        return Objects.equals(departId, that.departId) &&
                Objects.equals(postId, that.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departId, postId);
    }
}
