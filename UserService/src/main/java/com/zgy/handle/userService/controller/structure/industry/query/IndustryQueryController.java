package com.zgy.handle.userService.controller.structure.industry.query;

import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.model.dto.structure.IndustryQueryDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.base.QueryService;
import com.zgy.handle.userService.service.base.UpdateService;
import com.zgy.handle.userService.service.structure.industry.query.IndustryQueryService;
import com.zgy.handle.userService.service.structure.industry.update.IndustryUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "industry/query")
@Slf4j
public class IndustryQueryController extends QueryController<Industry,IndustryQueryDTO> {
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
    public List<SelectDTO> convertTtoSelectDTOList(List<Industry> industries) {
        return null;
    }

    @Override
    public List<IndustryQueryDTO> convertTtoU(List<Industry> industries) {
        return null;
    }

    @Override
    public IndustryQueryDTO convertTtoU(Industry industry) {
        return null;
    }

    @Override
    public Industry convertUtoT(IndustryQueryDTO industryQueryDTO) {
        return null;
    }
}
