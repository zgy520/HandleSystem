package com.zgy.handle.userservice.service.structure.enterprise.query;

import com.zgy.handle.userservice.model.dto.structure.EnterpriseQueryDTO;
import com.zgy.handle.userservice.model.structure.Enterprise;
import com.zgy.handle.userservice.service.base.QueryService;

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
