package com.zgy.handle.handleService.model.meta.structure.enterprise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.handleService.model.BaseModel;
import com.zgy.handle.handleService.model.meta.structure.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "handle_meta_body")
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
public class MetaBody extends BaseModel {
    @Embedded
    private Body body;

    @ManyToOne
    @JoinColumn(name = "headerId")
    @JsonIgnore
    private MetaHeader metaHeader;
}
