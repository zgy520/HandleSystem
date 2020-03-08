package com.zgy.handle.handleService.model.meta.dto.structure;

import com.zgy.handle.handleService.model.meta.structure.ColType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@ApiModel(value = "行业元数据标准的消息体")
public class IndustryMetaBodyDTO {
    private String id;
    @ApiModelProperty("字段名")
    private String name; //元数据的名称
    @ApiModelProperty("字段描述")
    private String description; // 描述
    @Enumerated(EnumType.STRING)
    @ApiModelProperty("字段类型，枚举")
    private ColType colType; // 元数据类型
    @ApiModelProperty("字段最大长度")
    private Integer fieldLen;  // 最大长度
    @ApiModelProperty("消息头的id")
    private String headerId;
}
