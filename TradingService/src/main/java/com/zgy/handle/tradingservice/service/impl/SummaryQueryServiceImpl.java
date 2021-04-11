package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.tradingservice.dto.SummaryDTO;
import com.zgy.handle.tradingservice.model.Summary;
import com.zgy.handle.tradingservice.repository.SummaryQueryRepository;
import com.zgy.handle.tradingservice.service.SummaryQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2021/4/7 23:11
 */
@Slf4j
@Service
public class SummaryQueryServiceImpl extends BaseQueryServiceImpl<Summary, SummaryDTO> implements SummaryQueryService {
    private SummaryQueryRepository summaryQueryRepository;
    public SummaryQueryServiceImpl(SummaryQueryRepository summaryQueryRepository) {
        super(summaryQueryRepository);
        this.summaryQueryRepository = summaryQueryRepository;
    }

    /**
     * 实现业务数据的查询
     *
     * @param dto
     * @return
     */
    @Override
    public Specification<Summary> querySpecification(SummaryDTO dto) {
        return null;
    }
}
