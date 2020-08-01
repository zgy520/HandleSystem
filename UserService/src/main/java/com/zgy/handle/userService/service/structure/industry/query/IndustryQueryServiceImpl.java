package com.zgy.handle.userService.service.structure.industry.query;

import com.zgy.handle.userService.model.dto.structure.IndustryQueryDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.repository.structure.industry.IndustryQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IndustryQueryServiceImpl extends QueryServiceImpl<Industry, IndustryQueryDTO> implements IndustryQueryService {
    private IndustryQueryRepository industryQueryRepository;

    public IndustryQueryServiceImpl(IndustryQueryRepository industryQueryRepository) {
        super(industryQueryRepository);
        this.industryQueryRepository = industryQueryRepository;
    }

    @Override
    public Specification<Industry> querySpecification(Pageable pageable, IndustryQueryDTO dto) {
        return null;
    }
}
