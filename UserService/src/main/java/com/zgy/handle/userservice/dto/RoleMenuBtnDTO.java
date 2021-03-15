package com.zgy.handle.userservice.dto;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: a4423
 * @date: 2021/3/9 22:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class RoleMenuBtnDTO  extends BaseDTO {
    private Long menuId;
    private String menuURL;
    private List btnId;
    private String menuName;
}
