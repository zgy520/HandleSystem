package com.zgy.handle.handleService.service.base;

import com.zgy.handle.handleService.model.base.RootInfoDTO;
import com.zgy.handle.handleService.model.base.SecondRootInfo;
import com.zgy.handle.handleService.repository.SystemRepository;
import com.zgy.handle.handleService.repository.base.SecondRootInfoRepository;
import com.zgy.handle.handleService.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondRootInfoService extends SystemService<SecondRootInfo, RootInfoDTO> {
    private SecondRootInfoRepository secondRootInfoRepository;
    @Autowired
    public SecondRootInfoService(SecondRootInfoRepository secondRootInfoRepository) {
        super(secondRootInfoRepository);
        this.secondRootInfoRepository = secondRootInfoRepository;
    }
}
