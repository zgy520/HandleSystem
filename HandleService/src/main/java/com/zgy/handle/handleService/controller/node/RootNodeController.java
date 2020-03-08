package com.zgy.handle.handleService.controller.node;

import com.zgy.handle.handleService.controller.SystemController;
import com.zgy.handle.handleService.controller.node.convert.RootNodeMapper;
import com.zgy.handle.handleService.model.SelectDTO;
import com.zgy.handle.handleService.model.node.RootNode;
import com.zgy.handle.handleService.model.node.RootNodeDTO;
import com.zgy.handle.handleService.service.SystemService;
import com.zgy.handle.handleService.service.node.RootNodeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "rootNode")
@Api(value = "根节点的接口",tags = "根节点")
public class RootNodeController extends SystemController<RootNode, RootNodeDTO> {
    private RootNodeService rootNodeService;
    @Autowired
    private RootNodeMapper rootNodeMapper;
    @Autowired
    public RootNodeController(RootNodeService rootNodeService) {
        super(rootNodeService);
        this.rootNodeService = rootNodeService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<RootNode> rootNodes) {
        return null;
    }

    @Override
    public List<RootNodeDTO> convertTtoU(List<RootNode> rootNodes) {
        return rootNodeMapper.toRootNodeDTOS(rootNodes);
    }

    @Override
    public RootNodeDTO convertTtoU(RootNode rootNode) {
        return rootNodeMapper.toRootNodeDTO(rootNode);
    }

    @Override
    public RootNode convertUtoT(RootNodeDTO rootNodeDTO) {
        return rootNodeMapper.toRootNode(rootNodeDTO);
    }
}
