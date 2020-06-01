package com.zgy.handle.handleService.controller.meta.meta.enterprise;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.controller.SystemController;
import com.zgy.handle.handleService.controller.meta.meta.enterprise.convert.MetaBodyMapper;
import com.zgy.handle.handleService.model.common.SelectDTO;
import com.zgy.handle.handleService.model.meta.dto.structure.MetaBodyDTO;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import com.zgy.handle.handleService.service.meta.structure.MetaBodyService;
import com.zgy.handle.handleService.service.meta.structure.MetaHeaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    private MetaHeaderService metaHeaderService;
    @Autowired
    public MetaBodyController(MetaBodyService metaBodyService) {
        super(metaBodyService);
        this.metaBodyService = metaBodyService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<MetaBody> metaBodies) {
        return null;
    }

    @PostMapping(value = "batchUpdate")
    public ResponseCode<List<MetaBodyDTO>> update(@Valid @RequestBody List<MetaBodyDTO> metaBodyDTOList){
        ResponseCode responseCode = ResponseCode.sucess();
        List<MetaBodyDTO> metaBodyDTOS = new ArrayList<>();
        for (MetaBodyDTO metaBodyDTO : metaBodyDTOList){
            metaBodyDTOS.add(convertTtoU(metaBodyService.update(metaBodyDTO,convertUtoT(metaBodyDTO)).getData()));
        }
        responseCode.setData(metaBodyDTOS);
        if (metaBodyDTOList.size() > 0){
            metaBodyService.createRegisterMetaDataXml(metaHeaderService.findById(Long.valueOf(metaBodyDTOList.get(0).getHeaderId())).get(),metaBodyMapper.toMetaBodys(metaBodyDTOList));
        }

        return responseCode;
    }

    @GetMapping(value = "getBodyByHeaderId/{headerId}")
    @ApiOperation(value = "根据元数据的头部id获取所有的字段内容")
    public ResponseCode<List<MetaBodyDTO>> getBodyByHeaderId(@PathVariable Long headerId,@PageableDefault(page = 1,size = 10) Pageable pageable){
        ResponseCode<List<MetaBodyDTO>> responseCode = ResponseCode.sucess();
        pageable = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), pageable.getSort());
        Page<MetaBody> page = metaBodyService.findByHeaderId(headerId,pageable);
        responseCode.setData(metaBodyMapper.toMetaBodyDTOS(page.getContent()));
        responseCode.setPageInfo(page);
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
