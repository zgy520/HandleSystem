package com.zgy.handle.userService.service.structure.enterprise.query;

import com.zgy.handle.userService.model.dto.structure.EnterpriseQueryDTO;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.service.base.QueryService;

import java.util.List;

public interface EnterpriseQueryService extends QueryService<Enterprise, EnterpriseQueryDTO> {

    /**
     * 查询企业信息
     * @param enterpriseQueryDTO
     * @return
     */
    List<Enterprise> findBySpecification(EnterpriseQueryDTO enterpriseQueryDTO);

    /**
     * 获取树形结构
     * @param enterpriseQueryDTOS
     * @return
     */
    List<EnterpriseQueryDTO> getTreeEnterpriseQueryDto(List<EnterpriseQueryDTO> enterpriseQueryDTOS);
}
