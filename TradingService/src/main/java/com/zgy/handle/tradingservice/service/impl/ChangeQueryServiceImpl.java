package com.zgy.handle.tradingservice.service.impl;

import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.tradingservice.dto.ChangeDTO;
import com.zgy.handle.tradingservice.model.Change;
import com.zgy.handle.tradingservice.model.Tracking;
import com.zgy.handle.tradingservice.model.Tracking_;
import com.zgy.handle.tradingservice.repository.ChangeQueryRepository;
import com.zgy.handle.tradingservice.service.ChangeQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2021/4/7 23:11
 */
@Service
@Slf4j
public class ChangeQueryServiceImpl extends BaseQueryServiceImpl<Change, ChangeDTO> implements ChangeQueryService {
    private ChangeQueryRepository changeQueryRepository;
    public ChangeQueryServiceImpl(ChangeQueryRepository changeQueryRepository) {
        super(changeQueryRepository);
        this.changeQueryRepository = changeQueryRepository;
    }

    /**
     * 实现业务数据的查询
     *
     * @param dto
     * @return
     */
    @Override
    public Specification<Change> querySpecification(ChangeDTO dto) {
        Specification<Change> specification = Specification
                .where(dto.getPlanId() == null ? null : changeQueryRepository.fieldEquals(Tracking_.PLAN_ID, dto.getPlanId().toString()));
        return specification;
    }
}
