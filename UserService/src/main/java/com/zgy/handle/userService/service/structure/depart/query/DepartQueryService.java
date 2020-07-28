package com.zgy.handle.userService.service.structure.depart.query;

import com.zgy.handle.userService.model.authority.depart.DepartQueryDTO;
import com.zgy.handle.userService.model.common.TransferDTO;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.service.base.QueryService;

import java.util.List;

public interface DepartQueryService extends QueryService<Department, DepartQueryDTO> {
    List<DepartQueryDTO> getDepartmentDtoList(List<DepartQueryDTO> departmentDTOList);
    List<TransferDTO> getAccountListByDepartId(Long departId);
    List<TransferDTO> getPostListByDepartId(Long departId);
}
