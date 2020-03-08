package com.zgy.handle.handleService.service.node;

import com.zgy.handle.handleService.model.node.RootNode;
import com.zgy.handle.handleService.model.node.RootNodeDTO;
import com.zgy.handle.handleService.repository.SystemRepository;
import com.zgy.handle.handleService.repository.node.RootNodeRepository;
import com.zgy.handle.handleService.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootNodeService extends SystemService<RootNode, RootNodeDTO> {
    private RootNodeRepository rootNodeRepository;
    @Autowired
    public RootNodeService(RootNodeRepository rootNodeRepository) {
        super(rootNodeRepository);
        this.rootNodeRepository = rootNodeRepository;
    }
}
