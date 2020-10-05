package com.zgy.handle.userservice.service.structure.depart.query;

import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.userservice.model.authority.depart.DepartQueryDTO;
import com.zgy.handle.userservice.model.common.TransferDTO;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.structure.Enterprise;

import java.util.List;

/**
 * @author a4423
 */
public interface DepartQueryService extends QueryService<Department, DepartQueryDTO> {
    List<DepartQueryDTO> getDepartmentDtoList(List<DepartQueryDTO> departmentDTOList);

    List<TransferDTO> getAccountListByDepartId(Long departId);

    List<TransferDTO> getPostListByDepartId(Long departId);

    /**
     * 获取部门所属的行业
     * @param departId 部门ID
     * @return 行业信息
     */
    Enterprise fetchIndustry(Long departId);
}
