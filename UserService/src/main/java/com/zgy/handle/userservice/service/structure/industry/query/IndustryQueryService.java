package com.zgy.handle.userservice.service.structure.industry.query;

import com.zgy.handle.common.model.common.TreeSelectDTO;
import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.userservice.model.dto.structure.IndustryQueryDTO;
import com.zgy.handle.userservice.model.structure.Industry;

import java.util.List;

public interface IndustryQueryService extends QueryService<Industry, IndustryQueryDTO> {
    /**
     * 获取行业得树形结构
     *
     * @param industryQueryDTOS
     * @return
     */
    List<IndustryQueryDTO> getTreeIndustryList(List<IndustryQueryDTO> industryQueryDTOS, IndustryQueryDTO dto);


    /**
     * 动态查询企业
     *
     * @param industryQueryDTO
     * @return
     */
    List<Industry> findBySpecificator(IndustryQueryDTO industryQueryDTO);
}
