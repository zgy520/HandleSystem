package com.zgy.handle.userservice.service.structure.depart.query;

import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.userservice.model.authority.depart.DepartQueryDTO;
import com.zgy.handle.userservice.model.common.TransferDTO;
import com.zgy.handle.userservice.model.structure.Department;

import java.util.List;

public interface DepartQueryService extends QueryService<Department, DepartQueryDTO> {
    List<DepartQueryDTO> getDepartmentDtoList(List<DepartQueryDTO> departmentDTOList);

    List<TransferDTO> getAccountListByDepartId(Long departId);

    List<TransferDTO> getPostListByDepartId(Long departId);
}
