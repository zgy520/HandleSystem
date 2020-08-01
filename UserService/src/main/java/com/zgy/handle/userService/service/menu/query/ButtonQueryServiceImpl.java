package com.zgy.handle.userService.service.menu.query;

import com.zgy.handle.userService.model.dto.menu.BtnDTO;
import com.zgy.handle.userService.model.menu.Button;
import com.zgy.handle.userService.model.menu.Button_;
import com.zgy.handle.userService.repository.menu.ButtonQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ButtonQueryServiceImpl extends QueryServiceImpl<Button, BtnDTO> implements ButtonQueryService {
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
