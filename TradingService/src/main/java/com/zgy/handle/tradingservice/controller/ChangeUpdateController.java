package com.zgy.handle.tradingservice.controller;

import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.tradingservice.dto.ChangeDTO;
import com.zgy.handle.tradingservice.mapper.ChangeMapper;
import com.zgy.handle.tradingservice.model.Change;
import com.zgy.handle.tradingservice.service.ChangeQueryService;
import com.zgy.handle.tradingservice.service.ChangeUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: a4423
 * @date: 2021/4/11 12:04
 */
@RestController
@RequestMapping(value = "change/update")
public class ChangeUpdateController extends BaseUpdateController<Change, ChangeDTO> {
    @Autowired
    private ChangeMapper changeMapper;
    private ChangeQueryService changeQueryService;
    private ChangeUpdateService changeUpdateService;

    public ChangeUpdateController(ChangeQueryService changeQueryService, ChangeUpdateService changeUpdateService) {
        super(changeUpdateService, changeQueryService);
        this.changeQueryService = changeQueryService;
        this.changeUpdateService = changeUpdateService;
    }
    /**
     * 将dto转为实体
     *
     * @param changeDTO
     * @return
     */
    @Override
    public Change convertUtoT(ChangeDTO changeDTO) {
        return changeMapper.toChange(changeDTO);
    }
}
