package com.zgy.handle.userservice.controller.structure.industry.query;

import com.zgy.handle.common.controller.base.BaseQueryController;
import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.common.model.common.TreeSelectDTO;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.model.dto.structure.IndustryQueryDTO;
import com.zgy.handle.userservice.model.structure.Industry;
import com.zgy.handle.userservice.service.structure.industry.query.IndustryQueryService;
import com.zgy.handle.userservice.service.structure.industry.update.IndustryUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a4423
 */
@RestController
@RequestMapping(value = "industry/query")
@Slf4j
public class IndustryQueryController extends BaseQueryController<Industry, IndustryQueryDTO> {
    @Autowired
    private IndustryQueryMapper industryQueryMapper;
    private IndustryQueryService industryQueryService;
    private IndustryUpdateService industryUpdateService;

    public IndustryQueryController(IndustryUpdateService industryUpdateService, IndustryQueryService industryQueryService) {
        super(industryUpdateService, industryQueryService);
        this.industryQueryService = industryQueryService;
        this.industryUpdateService = industryUpdateService;
    }

    @Override
    public ResponseCode<List<IndustryQueryDTO>> list(Pageable pageable, IndustryQueryDTO dto) {
        ResponseCode<List<IndustryQueryDTO>> responseCode = ResponseCode.sucess();
        List<Industry> industryList = industryQueryService.findBySpecificator(dto);
        List<IndustryQueryDTO> industryQueryDTOList = industryQueryMapper.toIndustryDTOList(industryList);
        responseCode.setData(industryQueryService.getTreeIndustryList(industryQueryDTOList, dto));
        return responseCode;
    }

    @Override
    public List<TreeSelectDTO> convertTtoTreeSelectDTOList(List<Industry> list) {
        List<TreeSelectDTO> industryQueryDTOList = industryQueryMapper.toTreeSelectDTOList(list);
        return industryQueryDTOList;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Industry> industries) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        industries.stream().forEach(industry -> {
            SelectDTO selectDTO = new SelectDTO(industry.getId().toString(), industry.getName(), industry.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public List<IndustryQueryDTO> convertTtoU(List<Industry> industries) {
        return industryQueryMapper.toIndustryDTOList(industries);
    }

    @Override
    public IndustryQueryDTO convertTtoU(Industry industry) {
        return industryQueryMapper.toIndustryDTO(industry);
    }

    @Override
    public Industry convertUtoT(IndustryQueryDTO industryQueryDTO) {
        return industryQueryMapper.toIndustry(industryQueryDTO);
    }
}
