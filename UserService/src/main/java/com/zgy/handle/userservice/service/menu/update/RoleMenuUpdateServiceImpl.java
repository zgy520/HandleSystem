package com.zgy.handle.userservice.service.menu.update;

import com.zgy.handle.userservice.model.menu.RoleMenu;
import com.zgy.handle.userservice.model.menu.RoleMenuPK;
import com.zgy.handle.userservice.repository.authority.role.RoleQueryRepository;
import com.zgy.handle.userservice.repository.menu.MenuQueryRepository;
import com.zgy.handle.userservice.repository.menu.RoleMenuUpdateRepository;
import com.zgy.handle.userservice.service.base.impl.BaseUpdateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a4423
 */
@Service
@Slf4j
public class RoleMenuUpdateServiceImpl extends BaseUpdateServiceImpl<RoleMenu, RoleMenu> implements RoleMenuUpdateService {
    private RoleMenuUpdateRepository roleMenuUpdateRepository;
    @Autowired
    private RoleQueryRepository roleQueryRepository;
    @Autowired
    private MenuQueryRepository menuQueryRepository;

    public RoleMenuUpdateServiceImpl(RoleMenuUpdateRepository roleMenuUpdateRepository) {
        super(roleMenuUpdateRepository);
        this.roleMenuUpdateRepository = roleMenuUpdateRepository;
    }


    @Override
    public void updateRoleMenu(Long roleId, List<Long> menuIdList) {
        List<RoleMenu> roleMenuList = new ArrayList<>();
        for (Long menuId : menuIdList) {
            RoleMenuPK roleMenuPK = new RoleMenuPK(roleId, menuId);
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleMenuPK(roleMenuPK);
            roleMenuList.add(roleMenu);
        }
        roleMenuUpdateRepository.saveAll(roleMenuList);
    }
}
