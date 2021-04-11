package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.tradingservice.dto.SummaryDTO;
import com.zgy.handle.tradingservice.model.Summary;
import com.zgy.handle.tradingservice.repository.SummaryUpdateRepository;
import com.zgy.handle.tradingservice.service.SummaryUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2021/4/8 22:57
 */
@Service
@Slf4j
public class SummaryUpdateServiceImpl extends BaseUpdateServiceImpl<Summary, SummaryDTO> implements SummaryUpdateService {
    private SummaryUpdateRepository summaryUpdateRepository;
    public SummaryUpdateServiceImpl(SummaryUpdateRepository summaryUpdateRepository) {
        super(summaryUpdateRepository);
        this.summaryUpdateRepository = summaryUpdateRepository;
    }
}
