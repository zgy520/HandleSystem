package com.zgy.handle.handleService.controller.meta.meta.enterprise;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.controller.SystemController;
import com.zgy.handle.handleService.controller.meta.meta.enterprise.convert.MetaBodyMapper;
import com.zgy.handle.handleService.controller.meta.meta.industry.convert.IndustryMetaBodyMapper;
import com.zgy.handle.handleService.model.SelectDTO;
import com.zgy.handle.handleService.model.meta.dto.structure.MetaBodyDTO;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import com.zgy.handle.handleService.service.SystemService;
import com.zgy.handle.handleService.service.meta.structure.MetaBodyService;
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
@RequestMapping(value = "metaBody")
@Slf4j
@Api(tags = "元数据字段信息接口")
public class MetaBodyController extends SystemController<MetaBody, MetaBodyDTO> {
    private MetaBodyService metaBodyService;
    @Autowired
    private MetaBodyMapper metaBodyMapper;
    @Autowired
    public MetaBodyController(MetaBodyService metaBodyService) {
        super(metaBodyService);
        this.metaBodyService = metaBodyService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<MetaBody> metaBodies) {
        return null;
    }


    @GetMapping(value = "getBodyByHeaderId/{headerId}")
    @ApiOperation(value = "根据元数据的头部id获取所有的字段内容")
    public ResponseCode<List<MetaBodyDTO>> getBodyByHeaderId(@PathVariable Long headerId){
        ResponseCode<List<MetaBodyDTO>> responseCode = ResponseCode.sucess();
        List<MetaBody> metaBodyList = metaBodyService.findByHeaderId(headerId);
        responseCode.setData(metaBodyMapper.toMetaBodyDTOS(metaBodyList));
        return responseCode;
    }

    @Override
    public List<MetaBodyDTO> convertTtoU(List<MetaBody> metaBodies) {
        return metaBodyMapper.toMetaBodyDTOS(metaBodies);
    }

    @Override
    public MetaBodyDTO convertTtoU(MetaBody metaBody) {
        return metaBodyMapper.toMetaBodyDTO(metaBody);
    }

    @Override
    public MetaBody convertUtoT(MetaBodyDTO metaBodyDTO) {
        return metaBodyMapper.toMetaBody(metaBodyDTO);
    }
}
