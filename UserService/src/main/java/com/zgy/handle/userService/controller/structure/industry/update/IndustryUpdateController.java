package com.zgy.handle.userService.controller.structure.industry.update;

import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.model.dto.structure.IndustryUpdateDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.service.structure.industry.query.IndustryQueryService;
import com.zgy.handle.userService.service.structure.industry.update.IndustryUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "industry/update")
@Slf4j
public class IndustryUpdateController extends UpdateController<Industry, IndustryUpdateDTO> {
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
