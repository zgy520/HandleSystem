package com.zgy.handle.userService.model.structure;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class IndustryEnterprisePK implements Serializable {
    private Long industryId;
    private Long enterpriseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndustryEnterprisePK that = (IndustryEnterprisePK) o;
        return industryId.equals(that.industryId) &&
                enterpriseId.equals(that.enterpriseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(industryId, enterpriseId);
    }
}
