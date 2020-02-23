package com.zgy.handle.userService.controller.user.convert;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.SelectDTO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


public interface SelectAccountMapper {
    @Mappings({
        @Mapping(source = "id",target = "value"),
        @Mapping(source = "name", target = "label")
    })
    List<SelectDTO> toAccountSelectDTOList(List<Account> accountList);
}
