package com.zgy.handle.userservice.controller.structure.industry.update;

import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.userservice.model.dto.structure.IndustryUpdateDTO;
import com.zgy.handle.userservice.model.structure.Industry;
import com.zgy.handle.userservice.service.structure.industry.query.IndustryQueryService;
import com.zgy.handle.userservice.service.structure.industry.update.IndustryUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "industry/update")
@Slf4j
public class IndustryUpdateController extends BaseUpdateController<Industry, IndustryUpdateDTO> {
    @Autowired
    private IndustryUpdateMapper industryUpdateMapper;
    private IndustryQueryService industryQueryService;
    private IndustryUpdateService industryUpdateService;

    public IndustryUpdateController(IndustryUpdateService industryUpdateService, IndustryQueryService industryQueryService) {
        super(industryUpdateService, industryQueryService);
        this.industryQueryService = industryQueryService;
        this.industryUpdateService = industryUpdateService;
    }

    @Override
    public Industry convertUtoT(IndustryUpdateDTO industryUpdateDTO) {
        return industryUpdateMapper.toIndustry(industryUpdateDTO);
    }
}
