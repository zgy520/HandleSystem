package com.zgy.handle.userservice.model.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: a4423
 * @date: 2020/9/12 21:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuButtonDTO {
    private Long menuId;
    private List<Long> btnIdList;
}
