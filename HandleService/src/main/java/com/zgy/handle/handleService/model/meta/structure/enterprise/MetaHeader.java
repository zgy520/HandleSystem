package com.zgy.handle.handleService.model.meta.structure.enterprise;

import com.zgy.handle.handleService.model.BaseModel;
import com.zgy.handle.handleService.model.meta.structure.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "handle_meta_header")
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
public class MetaHeader extends BaseModel {
    @Embedded
    private Header header;
    @ManyToOne
    @JoinColumn(name = "parentId")
    private MetaHeader parent; //上级节点

    private Long enterpriseId;
}
