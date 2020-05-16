package com.zgy.handle.handleService.repository.meta.structure;

import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetaHeaderRepository extends SystemRepository<MetaHeader> {
    /**
     * 根据企业id获取所有的元数据标准
     * @param enterpriseId
     * @return
     */
    List<MetaHeader> findByEnterpriseId(Long enterpriseId);

    /**
     * 根据特定名称获取消息头
     * 固定值
     * @param tableName
     * @return
     */
    MetaHeader findByTableName(String tableName);
}