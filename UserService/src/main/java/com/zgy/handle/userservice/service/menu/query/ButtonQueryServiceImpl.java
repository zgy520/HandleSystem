package com.zgy.handle.userservice.service.menu.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zgy.handle.userservice.model.dto.menu.BtnDTO;
import com.zgy.handle.userservice.model.menu.Button;
import com.zgy.handle.userservice.model.menu.Button_;
import com.zgy.handle.userservice.model.menu.Menu;
import com.zgy.handle.userservice.repository.menu.ButtonQueryRepository;
import com.zgy.handle.userservice.service.base.impl.BaseQueryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ButtonQueryServiceImpl extends BaseQueryServiceImpl<Button, BtnDTO> implements ButtonQueryService {
    private ButtonQueryRepository buttonQueryRepository;

    public ButtonQueryServiceImpl(ButtonQueryRepository buttonQueryRepository) {
        super(buttonQueryRepository);
        this.buttonQueryRepository = buttonQueryRepository;
    }

    @Override
    public Specification<Button> querySpecification(BtnDTO dto) {
        Specification<Button> specification = Specification
                .where(StringUtils.isBlank(dto.getName()) ? null : buttonQueryRepository.fieldLike(Button_.NAME, dto.getName()))
                .and(StringUtils.isBlank(dto.getCode()) ? null : buttonQueryRepository.fieldLike(Button_.CODE, dto.getCode()))
                .and(StringUtils.isBlank(dto.getNote()) ? null : buttonQueryRepository.fieldLike(Button_.NOTE, dto.getNote()));

        return specification;
    }
}
