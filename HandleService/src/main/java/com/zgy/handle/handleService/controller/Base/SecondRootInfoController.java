package com.zgy.handle.handleService.controller.Base;

import com.zgy.handle.handleService.controller.Base.convert.SecondRootInfoMapper;
import com.zgy.handle.handleService.controller.SystemController;
import com.zgy.handle.handleService.model.base.RootInfoDTO;
import com.zgy.handle.handleService.model.base.SecondRootInfo;
import com.zgy.handle.handleService.model.common.SelectDTO;
import com.zgy.handle.handleService.service.base.SecondRootInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "secondRoot")
public class SecondRootInfoController extends SystemController<SecondRootInfo, RootInfoDTO> {
    private SecondRootInfoService secondRootInfoService;
    @Autowired
    private SecondRootInfoMapper secondRootInfoMapper;
    @Autowired
    public SecondRootInfoController(SecondRootInfoService secondRootInfoService) {
        super(secondRootInfoService);
        this.secondRootInfoService = secondRootInfoService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<SecondRootInfo> secondRootInfos) {
        return null;
    }

    @Override
    public List<RootInfoDTO> convertTtoU(List<SecondRootInfo> secondRootInfos) {
        return secondRootInfoMapper.convertToRootInfos(secondRootInfos);
    }

    @Override
    public RootInfoDTO convertTtoU(SecondRootInfo secondRootInfo) {
        return secondRootInfoMapper.convertToRootInfo(secondRootInfo);
    }

    @Override
    public SecondRootInfo convertUtoT(RootInfoDTO rootInfoDTO) {
        return secondRootInfoMapper.convertToSecondRootInfo(rootInfoDTO);
    }

    @GetMapping(value = "getParentServer")
    public String getParentServer(){
        String serverInfo = "";
        SecondRootInfo secondRootInfo = secondRootInfoService.findAll().get(0);
        serverInfo = secondRootInfo.getParentIp() + ":" + secondRootInfo.getParentPort();
        return serverInfo;
    }
}
