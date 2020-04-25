package com.zgy.handle.handleService.model.base;

import com.zgy.handle.handleService.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "handle_enterprise_root_info")
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseRootInfo extends BaseModel {
    private String nodePrex;
    private String note;
}
