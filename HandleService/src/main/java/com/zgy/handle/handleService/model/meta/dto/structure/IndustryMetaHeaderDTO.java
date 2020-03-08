package com.zgy.handle.handleService.model.meta.dto.structure;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "行业标准元数据标准的表头")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class IndustryMetaHeaderDTO {
    private String id;
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
    @ApiModelProperty(value = "描述")
    private String note; // 说明
    @ApiModelProperty(value = "行业id")
    private String industryId;
    @ApiModelProperty(value = "上级标准id")
    private String parentId;
    private List<IndustryMetaHeaderDTO> children = new ArrayList<>();
}
