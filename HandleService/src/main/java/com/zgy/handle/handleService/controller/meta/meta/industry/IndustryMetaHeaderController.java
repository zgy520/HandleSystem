package com.zgy.handle.handleService.controller.meta.meta.industry;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.controller.SystemController;
import com.zgy.handle.handleService.controller.meta.meta.industry.convert.IndustryMetaHeaderMapper;
import com.zgy.handle.handleService.model.common.SelectDTO;
import com.zgy.handle.handleService.model.meta.dto.structure.IndustryMetaHeaderDTO;
import com.zgy.handle.handleService.model.meta.structure.industry.IndustryMetaHeader;
import com.zgy.handle.handleService.service.meta.structure.IndustryMetaHeaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "industryMetaHeader")
@Slf4j
@Api(tags = "行业元数标准头部的接口")
public class IndustryMetaHeaderController extends SystemController<IndustryMetaHeader, IndustryMetaHeaderDTO> {

    private IndustryMetaHeaderService industryMetaHeaderService;
    @Autowired
    private IndustryMetaHeaderMapper industryMetaHeaderMapper;
    @Autowired
    public IndustryMetaHeaderController(IndustryMetaHeaderService industryMetaHeaderService) {
        super(industryMetaHeaderService);
        this.industryMetaHeaderService = industryMetaHeaderService;
    }

    @GetMapping(value = "findIdByCode")
    @ApiOperation("根据handle码查id")
    public ResponseCode<String> findById(String handleCode){
        ResponseCode<String> responseCode = ResponseCode.sucess();
        IndustryMetaHeader industryMetaHeader = industryMetaHeaderService.findByCode(handleCode);

        responseCode.setData(industryMetaHeader.getId().toString());
        return responseCode;
    }

    @GetMapping(value = "getHeaderByIndustryId/{industryHeaderId}")
    @ApiOperation("根据行业id获取元数据标准列表")
    public ResponseCode<List<IndustryMetaHeaderDTO>> findByIndustryHeaderIld(@PathVariable Long industryHeaderId){
        ResponseCode<List<IndustryMetaHeaderDTO>> responseCode = ResponseCode.sucess();
        List<IndustryMetaHeader> industryMetaHeaderList = industryMetaHeaderService.findByIndustryId(industryHeaderId);
        responseCode.setData(industryMetaHeaderService.getIndustryMetaHeaderDTOList(industryMetaHeaderMapper.toIndustryMetaHeaderDTOS(industryMetaHeaderList)));
        return responseCode;
    }
    @Override
    public ResponseCode<List<IndustryMetaHeaderDTO>> list(Pageable pageable, IndustryMetaHeaderDTO dto) {
        ResponseCode<List<IndustryMetaHeaderDTO>> responseCode = ResponseCode.sucess();
        List<IndustryMetaHeader> industryMetaHeaderList = industryMetaHeaderService.findAll();
        responseCode.setData(industryMetaHeaderService.getIndustryMetaHeaderDTOList(industryMetaHeaderMapper.toIndustryMetaHeaderDTOS(industryMetaHeaderList)));
        return responseCode;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<IndustryMetaHeader> industryMetaHeaders) {
        return null;
    }

    @Override
    public List<IndustryMetaHeaderDTO> convertTtoU(List<IndustryMetaHeader> industryMetaHeaders) {
        return industryMetaHeaderMapper.toIndustryMetaHeaderDTOS(industryMetaHeaders);
    }

    @Override
    public IndustryMetaHeaderDTO convertTtoU(IndustryMetaHeader industryMetaHeader) {
        return industryMetaHeaderMapper.toIndustryMetaHeaderDTO(industryMetaHeader);
    }

    @Override
    public IndustryMetaHeader convertUtoT(IndustryMetaHeaderDTO industryMetaHeaderDTO) {
        return industryMetaHeaderMapper.toIndustryMetaHeader(industryMetaHeaderDTO);
    }
}
