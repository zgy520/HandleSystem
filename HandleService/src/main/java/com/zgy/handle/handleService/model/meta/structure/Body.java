package com.zgy.handle.handleService.model.meta.structure;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@ApiModel(value = "元数据标准内容")
public class Body {
    @ApiModelProperty("字段名")
    private String name; //元数据的名称
    @ApiModelProperty("字段描述")
    private String description; // 描述
    @Enumerated(EnumType.STRING)
    @ApiModelProperty("字段类型，枚举")
    private ColType colType; // 元数据类型
    @ApiModelProperty("字段最大长度")
    private Integer fieldLen;  // 最大长度
}
