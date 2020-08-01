package com.zgy.handle.userService.controller.structure.industry.query;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.model.dto.structure.IndustryQueryDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.structure.industry.query.IndustryQueryService;
import com.zgy.handle.userService.service.structure.industry.update.IndustryUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "industry/query")
@Slf4j
public class IndustryQueryController extends QueryController<Industry, IndustryQueryDTO> {
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
    public List<SelectDTO> convertTtoSelectDTOList(List<Industry> industries) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        industries.stream().forEach(industry -> {
            SelectDTO selectDTO = new SelectDTO(industry.getId().toString(),industry.getName(),industry.getId().toString());
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
