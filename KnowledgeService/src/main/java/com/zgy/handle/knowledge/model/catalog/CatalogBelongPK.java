package com.zgy.handle.knowledge.model.catalog;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//@Embeddable
@Data
public class CatalogBelongPK implements Serializable {
    private Long catalogId; // 目录id
    private Long businessId; // 业务id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogBelongPK that = (CatalogBelongPK) o;
        return Objects.equals(catalogId, that.catalogId) &&
                Objects.equals(businessId, that.businessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogId, businessId);
    }
}
