package com.zgy.handle.userservice.controller.menu;

import com.zgy.handle.common.controller.base.BaseQueryController;
import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.userservice.controller.menu.convert.ButtonMapper;
import com.zgy.handle.userservice.model.dto.menu.BtnDTO;
import com.zgy.handle.userservice.model.menu.Button;
import com.zgy.handle.userservice.service.menu.query.ButtonQueryService;
import com.zgy.handle.userservice.service.menu.update.ButtonUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "button/query")
public class ButtonQueryController extends BaseQueryController<Button, BtnDTO> {
    @Autowired
    private ButtonMapper buttonMapper;
    private ButtonUpdateService buttonUpdateService;
    private ButtonQueryService buttonQueryService;

    public ButtonQueryController(ButtonUpdateService buttonUpdateService, ButtonQueryService buttonQueryService) {
        super(buttonUpdateService, buttonQueryService);
        this.buttonQueryService = buttonQueryService;
        this.buttonUpdateService = buttonUpdateService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Button> buttonList) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        buttonList.stream().forEach(button -> {
            SelectDTO selectDTO = new SelectDTO(button.getId().toString(), button.getName(), button.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public List<BtnDTO> convertTtoU(List<Button> buttonList) {
        return buttonMapper.toBtnDTOList(buttonList);
    }

    @Override
    public BtnDTO convertTtoU(Button button) {
        return buttonMapper.toBtnDTO(button);
    }

    @Override
    public Button convertUtoT(BtnDTO btnDTO) {
        return buttonMapper.toButton(btnDTO);
    }
}
