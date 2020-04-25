package com.zgy.handle.handleService.controller.Base;

import com.zgy.handle.handleService.controller.Base.convert.EnterpriseRootInfoMapper;
import com.zgy.handle.handleService.controller.SystemController;
import com.zgy.handle.handleService.model.base.EnterpriseRootInfo;
import com.zgy.handle.handleService.model.base.RootInfoDTO;
import com.zgy.handle.handleService.model.common.SelectDTO;
import com.zgy.handle.handleService.service.base.EnterpriseRootInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "enterpriseRoot")
public class EnterpriseRootInfoController extends SystemController<EnterpriseRootInfo, RootInfoDTO> {
    private EnterpriseRootInfoService enterpriseRootInfoService;
    @Autowired
    private EnterpriseRootInfoMapper enterpriseRootInfoMapper;
    @Autowired
    public EnterpriseRootInfoController(EnterpriseRootInfoService enterpriseRootInfoService) {
        super(enterpriseRootInfoService);
        this.enterpriseRootInfoService = enterpriseRootInfoService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<EnterpriseRootInfo> enterpriseRootInfos) {
        return null;
    }

    @Override
    public List<RootInfoDTO> convertTtoU(List<EnterpriseRootInfo> enterpriseRootInfos) {
        return enterpriseRootInfoMapper.convertToRootInfos(enterpriseRootInfos);
    }

    @Override
    public RootInfoDTO convertTtoU(EnterpriseRootInfo enterpriseRootInfo) {
        return enterpriseRootInfoMapper.convertToRootInfo(enterpriseRootInfo);
    }

    @Override
    public EnterpriseRootInfo convertUtoT(RootInfoDTO rootInfoDTO) {
        return enterpriseRootInfoMapper.convertToEnterpriseRootInfo(rootInfoDTO);
    }
}
