package com.zgy.handle.userservice.service.menu.query;

import com.zgy.handle.userservice.model.menu.RoleMenu;
import com.zgy.handle.userservice.model.menu.RoleMenuPK;
import com.zgy.handle.userservice.repository.menu.RoleMenuQueryRepository;
import com.zgy.handle.userservice.service.base.impl.BaseQueryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: a4423
 * @date: 2020/9/12 16:42
 */
@Slf4j
@Service
public class RoleMenuQueryServiceImpl extends BaseQueryServiceImpl<RoleMenu, RoleMenu> implements RoleMenuQueryService {
    private RoleMenuQueryRepository roleMenuQueryRepository;

    public RoleMenuQueryServiceImpl(RoleMenuQueryRepository roleMenuQueryRepository) {
        super(roleMenuQueryRepository);
        this.roleMenuQueryRepository = roleMenuQueryRepository;
    }

    @Override
    public Specification<RoleMenu> querySpecification(RoleMenu dto) {
        return null;
    }

    @Override
    public List<Long> getMenuIdListByRoleId(Long roleId) {
        List<RoleMenu> roleMenuList = roleMenuQueryRepository.findByRoleMenuPK_RoleId(roleId);
        return roleMenuList.stream().map(RoleMenu::getRoleMenuPK).map(RoleMenuPK::getMenuId).collect(Collectors.toList());
    }

    @Override
    public List<Long> getRoleIdListByMenuId(Long menuId) {
        return null;
    }
}
