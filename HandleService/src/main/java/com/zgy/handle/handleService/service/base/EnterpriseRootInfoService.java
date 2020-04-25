package com.zgy.handle.handleService.service.base;

import com.zgy.handle.handleService.model.base.EnterpriseRootInfo;
import com.zgy.handle.handleService.model.base.RootInfoDTO;
import com.zgy.handle.handleService.repository.SystemRepository;
import com.zgy.handle.handleService.repository.base.EnterpriseRootInfoRepository;
import com.zgy.handle.handleService.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseRootInfoService extends SystemService<EnterpriseRootInfo, RootInfoDTO> {
    private EnterpriseRootInfoRepository enterpriseRootInfoRepository;
    @Autowired
    public EnterpriseRootInfoService(EnterpriseRootInfoRepository enterpriseRootInfoRepository) {
        super(enterpriseRootInfoRepository);
        this.enterpriseRootInfoRepository = enterpriseRootInfoRepository;
    }
}
