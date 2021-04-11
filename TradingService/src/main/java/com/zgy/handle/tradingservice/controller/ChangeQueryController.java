package com.zgy.handle.tradingservice.controller;

import com.zgy.handle.common.controller.base.BaseQueryController;
import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.tradingservice.dto.ChangeDTO;
import com.zgy.handle.tradingservice.mapper.ChangeMapper;
import com.zgy.handle.tradingservice.model.Change;
import com.zgy.handle.tradingservice.service.ChangeQueryService;
import com.zgy.handle.tradingservice.service.ChangeUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: a4423
 * @date: 2021/4/11 12:00
 */
@RestController
@RequestMapping(value = "change/query")
public class ChangeQueryController extends BaseQueryController<Change, ChangeDTO> {
    @Autowired
    private ChangeMapper changeMapper;
    private ChangeQueryService changeQueryService;
    private ChangeUpdateService changeUpdateService;

    public ChangeQueryController(ChangeQueryService changeQueryService, ChangeUpdateService changeUpdateService) {
        super(changeUpdateService, changeQueryService);
        this.changeQueryService = changeQueryService;
        this.changeUpdateService = changeUpdateService;
    }

    /**
     * 将实体列表转化为id和text列表
     *
     * @param changes
     * @return
     */
    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Change> changes) {
        return null;
    }

    /**
     * 实体列表转化为dto列表
     *
     * @param changes 实体列表
     * @return dto列表
     */
    @Override
    public List<ChangeDTO> convertTtoU(List<Change> changes) {
        return changeMapper.toChangeList(changes);
    }

    /**
     * 将实体对象转为dto
     *
     * @param change
     * @return
     */
    @Override
    public ChangeDTO convertTtoU(Change change) {
        return changeMapper.toChangeDTO(change);
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
