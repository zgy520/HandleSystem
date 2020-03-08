package com.zgy.handle.handleService.model.meta.structure.industry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.handleService.model.BaseModel;
import com.zgy.handle.handleService.model.meta.structure.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "handle_industry_meta_body")
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
public class IndustryMetaBody extends BaseModel {
    @Embedded
    private Body body;

    @ManyToOne
    @JoinColumn(name = "headerId")
    @JsonIgnore
    private IndustryMetaHeader industryMetaHeader;
}
