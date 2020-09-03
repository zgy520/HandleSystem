package com.zgy.handle.handleService.repository.meta.structure;

import com.zgy.handle.handleService.model.meta.structure.industry.IndustryMetaHeader;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryMetaHeaderRepository extends SystemRepository<IndustryMetaHeader> {
    /**
     * 根据行业id获取所有的行业元数据标准
     * @param industryId
     * @return
     */
    List<IndustryMetaHeader> findByIndustryId(Long industryId);

    List<IndustryMetaHeader> findByHeader_IdentityNum(String handleCode);
}
