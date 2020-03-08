package com.zgy.handle.handleService.model.node;

import com.zgy.handle.handleService.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "handle_root_node")
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class RootNode extends BaseModel {
    private String name;
    private String prex;
    private String ip;
    private String location; // 位置
}
