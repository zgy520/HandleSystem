package com.zgy.handle.userservice.model.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userservice.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "system_industry")
@Data
@Slf4j
@Audited
public class Industry extends BaseModel implements Serializable {
    private String code;
    private String name;
    private String shortName;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parentId",referencedColumnName = "id")
    @JsonIgnore
    private Industry parent;
}
