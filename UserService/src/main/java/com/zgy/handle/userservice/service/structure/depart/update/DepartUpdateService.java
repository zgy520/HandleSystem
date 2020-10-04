package com.zgy.handle.userservice.service.structure.depart.update;

import com.zgy.handle.common.service.base.UpdateService;
import com.zgy.handle.userservice.model.authority.depart.DepartUpdateDTO;
import com.zgy.handle.userservice.model.structure.Department;

public interface DepartUpdateService extends UpdateService<Department, DepartUpdateDTO> {
    String relateUser(Long departId, String selectedUserList);

    String relatePost(Long departId, String selectedPostList);
}
