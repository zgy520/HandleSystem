package com.zgy.handle.handleService.controller.meta.meta.industry;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.controller.SystemController;
import com.zgy.handle.handleService.controller.meta.meta.industry.convert.IndustryMetaBodyMapper;
import com.zgy.handle.handleService.model.common.SelectDTO;
import com.zgy.handle.handleService.model.meta.dto.structure.IndustryMetaBodyDTO;
import com.zgy.handle.handleService.model.meta.structure.industry.IndustryMetaBody;
import com.zgy.handle.handleService.service.meta.structure.IndustryMetaBodyService;
import com.zgy.handle.handleService.service.meta.structure.IndustryMetaHeaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "industryMetaBody")
@Slf4j
@Api(tags = "行业元数据字段信息接口")
public class IndustryMetaBodyController extends SystemController<IndustryMetaBody, IndustryMetaBodyDTO> {
    private IndustryMetaBodyService industryMetaBodyService;
    @Autowired
    private IndustryMetaBodyMapper industryMetaBodyMapper;
    @Autowired
    private IndustryMetaHeaderService industryMetaHeaderService;
    public IndustryMetaBodyController(IndustryMetaBodyService industryMetaBodyService) {
        super(industryMetaBodyService);
        this.industryMetaBodyService = industryMetaBodyService;
    }

    @PostMapping(value = "batchUpdate")
    public ResponseCode<List<IndustryMetaBodyDTO>> update(@Valid @RequestBody List<IndustryMetaBodyDTO> metaBodyDTOList){
        ResponseCode responseCode = ResponseCode.sucess();
        List<IndustryMetaBodyDTO> metaBodyDTOS = new ArrayList<>();
        for (IndustryMetaBodyDTO metaBodyDTO : metaBodyDTOList){
            metaBodyDTOS.add(convertTtoU(industryMetaBodyService.update(metaBodyDTO,convertUtoT(metaBodyDTO)).getData()));
        }

        responseCode.setData(metaBodyDTOS);

        // if (metaBodyDTOList.size() > 0){ // 消息体
            industryMetaBodyService.createRegisterMetaDataXml(industryMetaHeaderService.findById(Long.valueOf(metaBodyDTOList.get(0).getHeaderId())).get(),industryMetaBodyMapper.toIndustryMetaBodS(metaBodyDTOList));
        // }

        return responseCode;
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
