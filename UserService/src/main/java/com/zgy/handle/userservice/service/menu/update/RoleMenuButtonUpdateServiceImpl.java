package com.zgy.handle.userservice.service.menu.update;

import com.zgy.handle.userservice.model.menu.MenuButtonDTO;
import com.zgy.handle.userservice.model.menu.RoleMenuButton;
import com.zgy.handle.userservice.model.menu.RoleMenuButtonPK;
import com.zgy.handle.userservice.repository.menu.RoleMenuButtonUpdateRepository;
import com.zgy.handle.userservice.service.base.impl.BaseUpdateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: a4423
 * @date: 2020/9/12 20:58
 */
@Slf4j
@Service
public class RoleMenuButtonUpdateServiceImpl extends BaseUpdateServiceImpl<RoleMenuButton, RoleMenuButton> implements RoleMenuButtonUpdateService {
    private RoleMenuButtonUpdateRepository roleMenuButtonUpdateRepository;

    public RoleMenuButtonUpdateServiceImpl(RoleMenuButtonUpdateRepository roleMenuButtonUpdateRepository) {
        super(roleMenuButtonUpdateRepository);
        this.roleMenuButtonUpdateRepository = roleMenuButtonUpdateRepository;
    }

    @Override
    public void updateRoleMenuButton(Long roleId, List<MenuButtonDTO> menuButtonDTOList) {
        List<RoleMenuButton> roleMenuButtonList = new ArrayList<>();
        for (MenuButtonDTO menuButtonDTO : menuButtonDTOList) {
            List<Long> btnList = menuButtonDTO.getBtnIdList();
            for (Long btnId : btnList) {
                RoleMenuButton roleMenuButton = new RoleMenuButton();
                RoleMenuButtonPK roleMenuButtonPK = new RoleMenuButtonPK(roleId, menuButtonDTO.getMenuId(), btnId);
                roleMenuButton.setRoleMenuButtonPK(roleMenuButtonPK);
                roleMenuButtonList.add(roleMenuButton);
            }
        }
        roleMenuButtonUpdateRepository.saveAll(roleMenuButtonList);
    }
}
