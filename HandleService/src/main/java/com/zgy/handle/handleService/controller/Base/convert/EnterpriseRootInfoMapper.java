package com.zgy.handle.handleService.controller.Base.convert;

import com.zgy.handle.handleService.model.base.EnterpriseRootInfo;
import com.zgy.handle.handleService.model.base.RootInfoDTO;
import com.zgy.handle.handleService.model.base.SecondRootInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseRootInfoMapper {
    RootInfoDTO convertToRootInfo(EnterpriseRootInfo enterpriseRootInfo);
    List<RootInfoDTO> convertToRootInfos(List<EnterpriseRootInfo> enterpriseRootInfoList);
    EnterpriseRootInfo convertToEnterpriseRootInfo(RootInfoDTO rootInfoDTO);
}
