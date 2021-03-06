package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.structure.IndustryEnterprise;
import com.zgy.handle.userService.repository.structure.IndustryEnterpriseRepository;
import com.zgy.handle.userService.service.SystemService;
import org.springframework.stereotype.Service;

@Service
public class IndustryEnterpriseService extends SystemService<IndustryEnterprise,IndustryEnterprise> {
    private IndustryEnterpriseRepository industryEnterpriseRepository;
    public IndustryEnterpriseService(IndustryEnterpriseRepository industryEnterpriseRepository) {
        super(industryEnterpriseRepository);
        this.industryEnterpriseRepository = industryEnterpriseRepository;
    }
}
