package com.zgy.handle.userService.controller.menu;

import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.controller.menu.convert.ButtonMapper;
import com.zgy.handle.userService.model.dto.menu.BtnDTO;
import com.zgy.handle.userService.model.menu.Button;
import com.zgy.handle.userService.service.menu.query.ButtonQueryService;
import com.zgy.handle.userService.service.menu.update.ButtonUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "btn/update")
@Slf4j
public class ButtonUpdateController extends UpdateController<Button, BtnDTO> {
    @Autowired
    private ButtonMapper buttonMapper;
    private ButtonUpdateService buttonUpdateService;
    private ButtonQueryService buttonQueryService;

    public ButtonUpdateController(ButtonUpdateService buttonUpdateService, ButtonQueryService buttonQueryService) {
        super(buttonUpdateService, buttonQueryService);
        this.buttonQueryService = buttonQueryService;
        this.buttonUpdateService = buttonUpdateService;
    }

    @Override
    public Button convertUtoT(BtnDTO btnDTO) {
        return buttonMapper.toButton(btnDTO);
    }
}
