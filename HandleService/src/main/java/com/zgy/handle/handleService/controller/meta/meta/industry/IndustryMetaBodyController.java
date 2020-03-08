package com.zgy.handle.handleService.controller.meta.meta.industry;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.controller.SystemController;
import com.zgy.handle.handleService.controller.meta.meta.industry.convert.IndustryMetaBodyMapper;
import com.zgy.handle.handleService.model.SelectDTO;
import com.zgy.handle.handleService.model.meta.dto.structure.IndustryMetaBodyDTO;
import com.zgy.handle.handleService.model.meta.structure.industry.IndustryMetaBody;
import com.zgy.handle.handleService.service.meta.structure.IndustryMetaBodyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "industryMetaBody")
@Slf4j
@Api(tags = "行业元数据字段信息接口")
public class IndustryMetaBodyController extends SystemController<IndustryMetaBody, IndustryMetaBodyDTO> {
    private IndustryMetaBodyService industryMetaBodyService;
    @Autowired
    private IndustryMetaBodyMapper industryMetaBodyMapper;
    public IndustryMetaBodyController(IndustryMetaBodyService industryMetaBodyService) {
        super(industryMetaBodyService);
        this.industryMetaBodyService = industryMetaBodyService;
    }

    @GetMapping(value = "getBodyByHeaderId/{industryHeaderId}")
    @ApiOperation("根据行业的标准头获取内容")
    public ResponseCode<List<IndustryMetaBodyDTO>> findByHeaderId(@PathVariable Long industryHeaderId){
        ResponseCode<List<IndustryMetaBodyDTO>> responseCode = ResponseCode.sucess();
        List<IndustryMetaBody> industryMetaBodyList = industryMetaBodyService.findByIndustryHeaderId(industryHeaderId);
        responseCode.setData(industryMetaBodyMapper.toIndustryMetaBodyDTOS(industryMetaBodyList));
        return responseCode;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<IndustryMetaBody> industryMetaBodies) {
        return null;
    }

    @Override
    public List<IndustryMetaBodyDTO> convertTtoU(List<IndustryMetaBody> industryMetaBodies) {
        return industryMetaBodyMapper.toIndustryMetaBodyDTOS(industryMetaBodies);
    }

    @Override
    public IndustryMetaBodyDTO convertTtoU(IndustryMetaBody industryMetaBody) {
        return industryMetaBodyMapper.toIndustryMetaBodyDTO(industryMetaBody);
    }

    @Override
    public IndustryMetaBody convertUtoT(IndustryMetaBodyDTO industryMetaBodyDTO) {
        return industryMetaBodyMapper.toIndustryMetaBody(industryMetaBodyDTO);
    }
}
