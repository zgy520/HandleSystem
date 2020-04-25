package com.zgy.handle.handleService.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.handleService.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "handle_second_root_info")
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class SecondRootInfo extends BaseModel {
    private String nodePrex; // 节点前缀
    private String note;
}
