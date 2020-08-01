package com.zgy.handle.userService.service.structure.industry.query;

import com.zgy.handle.userService.model.dto.structure.IndustryQueryDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.service.base.QueryService;

import java.util.List;

public interface IndustryQueryService extends QueryService<Industry, IndustryQueryDTO> {
    /**
     * 获取行业得树形结构
     * @param industryQueryDTOS
     * @return
     */
    List<IndustryQueryDTO> getTreeIndustryList(List<IndustryQueryDTO> industryQueryDTOS, IndustryQueryDTO dto);

    /**
     * 动态查询企业
     * @param industryQueryDTO
     * @return
     */
    List<Industry> findBySpecificator(IndustryQueryDTO industryQueryDTO);
}
