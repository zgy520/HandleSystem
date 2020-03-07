package com.zgy.handle.knowledge.repository.dispatch;

import com.zgy.handle.knowledge.model.common.ResourceDispatch;
import com.zgy.handle.knowledge.model.common.ResourceLevel;
import com.zgy.handle.knowledge.model.common.ResourceType;
import com.zgy.handle.knowledge.repository.KnowledgeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceDispatchRepository extends KnowledgeRepository<ResourceDispatch> {
    /**
     * 根据资源类型获取所有的资源信息
     * @param resourceType
     * @return
     */
    List<ResourceDispatch> findByResourceType(ResourceType resourceType);

    /**
     * 根据资源类型和资源级别获取资源信息
     * @param resourceType
     * @param resourceLevel
     * @return
     */
    List<ResourceDispatch> findByResourceTypeAndResourceLevel(ResourceType resourceType, ResourceLevel resourceLevel);

    /**
     * 根据资源类型和资源级别以及人员级别获取资源信息
     * @param resourceType
     * @param resourceLevel
     * @param personalLevelId
     * @return
     */
    List<ResourceDispatch> findByResourceTypeAndResourceLevelAndPersonalLevelId(ResourceType resourceType,ResourceLevel resourceLevel,Long personalLevelId);

    /**
     * 根据资源类型和人员级别的列表获取资源信息
     * @param resourceType
     * @param personalLevelList
     * @return
     */
    List<ResourceDispatch> findByResourceTypeAndPersonalLevelIdIn(ResourceType resourceType,List<Long> personalLevelList);
}
