package com.zgy.handle.userservice.controller.menu.convert;

import com.zgy.handle.userservice.model.dto.menu.BtnDTO;
import com.zgy.handle.userservice.model.menu.Button;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ButtonMapper {
    Button toButton(BtnDTO btnDTO);

    BtnDTO toBtnDTO(Button button);

    List<BtnDTO> toBtnDTOList(List<Button> buttonList);
}
