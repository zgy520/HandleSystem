package com.zgy.handle.userService.service.structure.depart.update;

import com.zgy.handle.userService.model.authority.depart.DepartUpdateDTO;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.service.base.UpdateService;

public interface DepartUpdateService extends UpdateService<Department, DepartUpdateDTO> {
    String relateUser(Long departId, String selectedUserList);
    String relatePost(Long departId, String selectedPostList);
}
