package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.tradingservice.dto.ChangeDTO;
import com.zgy.handle.tradingservice.model.Change;
import com.zgy.handle.tradingservice.repository.ChangeUpdateRepository;
import com.zgy.handle.tradingservice.service.ChangeUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2021/4/8 22:55
 */
@Service
@Slf4j
public class ChangeUpdateServiceImpl extends BaseUpdateServiceImpl<Change, ChangeDTO> implements ChangeUpdateService {
    private ChangeUpdateRepository changeUpdateRepository;
    public ChangeUpdateServiceImpl(ChangeUpdateRepository changeUpdateRepository) {
        super(changeUpdateRepository);
        this.changeUpdateRepository = changeUpdateRepository;
    }
}
