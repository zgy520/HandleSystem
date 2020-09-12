package com.zgy.handle.userservice.service.structure;

import com.zgy.handle.userservice.model.structure.IndustryEnterprise;
import com.zgy.handle.userservice.repository.structure.IndustryEnterpriseRepository;
import com.zgy.handle.userservice.service.BaseSystemService;
import org.springframework.stereotype.Service;

@Service
public class IndustryEnterpriseServiceBase extends BaseSystemService<IndustryEnterprise,IndustryEnterprise> {
    private IndustryEnterpriseRepository industryEnterpriseRepository;
    public IndustryEnterpriseServiceBase(IndustryEnterpriseRepository industryEnterpriseRepository) {
        super(industryEnterpriseRepository);
        this.industryEnterpriseRepository = industryEnterpriseRepository;
    }
}
