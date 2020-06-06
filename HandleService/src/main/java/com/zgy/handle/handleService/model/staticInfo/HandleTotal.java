package com.zgy.handle.handleService.model.staticInfo;

import com.zgy.handle.handleService.model.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "handle_static_total")
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class HandleTotal extends BaseModel {
    @Enumerated(EnumType.STRING)
    private HandleType handleType;
    private Integer totalCount; // 总数量
}
