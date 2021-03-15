package com.zgy.handle.userservice.repository.authority.role;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.menu.RoleMenuButton;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuBtnQueryRepository extends QueryRepository<RoleMenuButton> {
   /* List<RoleMenuButton> findByRoleId (Long parentId);
    List<RoleMenuButton> findByMenuId (Long parentId);
    List<RoleMenuButton> findByMenuIdIn (List<Long> MenuIdInList);*/
//    List<RoleMenuButton> findByRoleIdIn (List<Long> RoleIdInList);
//    List<RoleMenuButton> findByRoleIdAndMenuId(Long roleId, Long menuId);

}
