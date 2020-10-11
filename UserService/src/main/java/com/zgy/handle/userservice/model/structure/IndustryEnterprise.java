package com.zgy.handle.userservice.model.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "system_industry_enterprise")
public class IndustryEnterprise implements Serializable {
    @EmbeddedId
    private IndustryEnterprisePK industryEnterprisePK;

    @ManyToOne
    @JoinColumn(name = "industryId")
    @MapsId("industryId")
    @JsonIgnore
    private Industry industry;

    @ManyToOne
    @JoinColumn(name = "enterpriseId")
    @MapsId("enterpriseId")
    @JsonIgnore
    private Enterprise enterprise;

    private int weight; // 所属的该行业在其中所占的权重，1到10之间，值越大表明权重越大
}