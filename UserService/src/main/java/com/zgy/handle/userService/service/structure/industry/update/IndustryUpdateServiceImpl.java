package com.zgy.handle.userService.service.structure.industry.update;

import com.zgy.handle.userService.model.dto.structure.IndustryUpdateDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.repository.structure.industry.IndustryUpdateRepository;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IndustryUpdateServiceImpl extends UpdateServiceImpl<Industry, IndustryUpdateDTO> implements IndustryUpdateService {
    private IndustryUpdateRepository industryUpdateRepository;

    public IndustryUpdateServiceImpl(IndustryUpdateRepository industryUpdateRepository) {
        super(industryUpdateRepository);
        this.industryUpdateRepository = industryUpdateRepository;
    }
}
