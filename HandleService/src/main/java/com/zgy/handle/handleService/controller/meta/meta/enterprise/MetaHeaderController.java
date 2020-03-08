package com.zgy.handle.handleService.controller.meta.meta.enterprise;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.controller.SystemController;
import com.zgy.handle.handleService.controller.meta.meta.enterprise.convert.MetaHeaderMapper;
import com.zgy.handle.handleService.model.SelectDTO;
import com.zgy.handle.handleService.model.meta.dto.structure.MetaHeaderDTO;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.service.meta.structure.MetaHeaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "metaHeader")
@Slf4j
@Api(tags = "元数标准头部的接口")
public class MetaHeaderController extends SystemController<MetaHeader, MetaHeaderDTO> {
    private MetaHeaderService metaHeaderService;
    @Autowired
    private MetaHeaderMapper metaHeaderMapper;
    @Autowired
    public MetaHeaderController(MetaHeaderService metaHeaderService) {
        super(metaHeaderService);
        this.metaHeaderService = metaHeaderService;
    }

    @GetMapping(value = "getMetaHeaderByUser")
    @ApiOperation(value = "根据用户的企业获取对应的元数据标准，可传入企业id，用于测试")
    public ResponseCode<List<MetaHeaderDTO>> getMetaHeaderByUser(Long enterpriseId){
        ResponseCode<List<MetaHeaderDTO>> responseCode = ResponseCode.sucess();
        List<MetaHeader> metaHeaderList = metaHeaderService.getMetaHeaderListByEnterpriseId(enterpriseId);
        responseCode.setData(metaHeaderService.getMetaHeaderDTOList(metaHeaderMapper.toMetaHeaderDTOS(metaHeaderList)));
        return responseCode;
    }

    @Override
    public ResponseCode<List<MetaHeaderDTO>> list(Pageable pageable, MetaHeaderDTO dto) {
        ResponseCode<List<MetaHeaderDTO>> responseCode = ResponseCode.sucess();
        List<MetaHeader> metaHeaderList = metaHeaderService.findAll();
        responseCode.setData(metaHeaderService.getMetaHeaderDTOList(metaHeaderMapper.toMetaHeaderDTOS(metaHeaderList)));
        return responseCode;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<MetaHeader> metaHeaders) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        metaHeaders.stream().forEach(metaHeader -> {
            SelectDTO selectDTO = new SelectDTO(metaHeader.getId().toString(),metaHeader.getHeader().getAlias());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public List<MetaHeaderDTO> convertTtoU(List<MetaHeader> metaHeaders) {
        return metaHeaderMapper.toMetaHeaderDTOS(metaHeaders);
    }

    @Override
    public MetaHeaderDTO convertTtoU(MetaHeader metaHeader) {
        return metaHeaderMapper.toMetaHeaderDTO(metaHeader);
    }

    @Override
    public MetaHeader convertUtoT(MetaHeaderDTO metaHeaderDTO) {
        return metaHeaderMapper.toMetaHeader(metaHeaderDTO);
    }
}
