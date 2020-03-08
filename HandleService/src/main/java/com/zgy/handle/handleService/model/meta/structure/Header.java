package com.zgy.handle.handleService.model.meta.structure;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
@ApiModel(value = "元数据标准头部")
public class Header {
    @ApiModelProperty(value = "唯一标识号，即handle码")
    private String identityNum; // 标识号
    @ApiModelProperty(value = "版本号")
    private String version; // 版本号
    @ApiModelProperty(value = "名称")
    private String name; // 名称
    @ApiModelProperty(value = "别名")
    private String alias; // 别名
    @ApiModelProperty(value = "状态")
    private Boolean state; // 状态
    /*@ApiModelProperty(value = "描述")
    private String description; // 说明*/
}
