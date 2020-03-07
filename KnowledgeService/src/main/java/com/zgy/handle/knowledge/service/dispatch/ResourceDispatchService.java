package com.zgy.handle.knowledge.service.dispatch;

import com.zgy.handle.knowledge.model.PostList;
import com.zgy.handle.knowledge.model.common.ResourceDispatch;
import com.zgy.handle.knowledge.model.common.ResourceLevel;
import com.zgy.handle.knowledge.model.common.ResourceType;
import com.zgy.handle.knowledge.repository.dispatch.ResourceDispatchRepository;
import com.zgy.handle.knowledge.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceDispatchService extends KnowledgeService<ResourceDispatch, ResourceDispatch> {
    private ResourceDispatchRepository resourceDispatchRepository;
    @Autowired
    public ResourceDispatchService(ResourceDispatchRepository resourceDispatchRepository) {
        super(resourceDispatchRepository);
        this.resourceDispatchRepository = resourceDispatchRepository;
    }

    public void addResourceDispatch(Long resourceId, ResourceType resourceType, ResourceLevel resourceLevel, List<Long> personalLevelIdList){
        List<ResourceDispatch> resourceDispatchList = new ArrayList<>();
        for (Long personalId : personalLevelIdList){
            ResourceDispatch resourceDispatch = new ResourceDispatch();
            resourceDispatch.setResourceId(resourceId);
            resourceDispatch.setResourceType(resourceType);
            resourceDispatch.setResourceLevel(resourceLevel);
            resourceDispatch.setPersonalLevelId(personalId);
            resourceDispatchList.add(resourceDispatch);
        }
        resourceDispatchRepository.saveAll(resourceDispatchList);
    }

    /**
     * 添加资源分发情况，自动判断资源上传的人员级别
     * @param resourceId
     * @param resourceType
     */
    public void addResourceDispatch(Long resourceId, ResourceType resourceType){
        ResourceDispatch resourceDispatch = new ResourceDispatch();
        String postCodeList = this.getPostName();
        if (postCodeList.contains(PostList.Admin.toString())){
            resourceDispatch.setResourceLevel(ResourceLevel.ENTERPRISE);
            resourceDispatch.setPersonalLevelId(Long.valueOf(this.getEnterpriseId()));
        }else if (postCodeList.contains(PostList.departLeader.toString())){
            resourceDispatch.setResourceLevel(ResourceLevel.DEPART);
            resourceDispatch.setPersonalLevelId(Long.valueOf(this.getDepartId()));
        }else {
            resourceDispatch.setResourceLevel(ResourceLevel.PERSONAL);
            resourceDispatch.setPersonalLevelId(Long.valueOf(this.getPersonalId()));
        }
        resourceDispatch.setResourceId(resourceId);   // 资源id
        resourceDispatch.setResourceType(resourceType); // 资源类型
        resourceDispatchRepository.save(resourceDispatch);
    }

}
