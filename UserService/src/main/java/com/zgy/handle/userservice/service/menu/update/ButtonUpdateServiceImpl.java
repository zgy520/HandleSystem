package com.zgy.handle.userservice.service.menu.update;

import com.zgy.handle.userservice.model.common.UniqueInfo;
import com.zgy.handle.userservice.model.dto.menu.BtnDTO;
import com.zgy.handle.userservice.model.menu.Button;
import com.zgy.handle.userservice.repository.menu.ButtonQueryRepository;
import com.zgy.handle.userservice.repository.menu.ButtonUpdateRepository;
import com.zgy.handle.userservice.service.base.impl.BaseUpdateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ButtonUpdateServiceImpl extends BaseUpdateServiceImpl<Button, BtnDTO> implements ButtonUpdateService {
    @Autowired
    private ButtonQueryRepository buttonQueryRepository;
    private ButtonUpdateRepository buttonUpdateRepository;

    public ButtonUpdateServiceImpl(ButtonUpdateRepository buttonUpdateRepository) {
        super(buttonUpdateRepository);
        this.buttonUpdateRepository = buttonUpdateRepository;
    }

    @Override
    public UniqueInfo checkUnique(BtnDTO btnDTO, Button button) {
        Integer count = StringUtils.isBlank(btnDTO.getId()) ? buttonQueryRepository.countByNameOrCode(btnDTO.getName(), btnDTO.getCode()) :
                buttonQueryRepository.countByNameOrCodeAndIdNot(btnDTO.getName(), btnDTO.getCode(), btnDTO.getId());
        if (count != null){
            return UniqueInfo.getUniqueInfo("按钮名称或编码重复，请确认!");
        }
        return super.checkUnique(btnDTO, button);
    }
}
