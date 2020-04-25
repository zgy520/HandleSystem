package com.zgy.handle.handleService.controller.Base.convert;

import com.zgy.handle.handleService.model.base.RootInfoDTO;
import com.zgy.handle.handleService.model.base.SecondRootInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SecondRootInfoMapper {
    RootInfoDTO convertToRootInfo(SecondRootInfo secondRootInfo);
    List<RootInfoDTO> convertToRootInfos(List<SecondRootInfo> secondRootInfoList);
    SecondRootInfo convertToSecondRootInfo(RootInfoDTO rootInfoDTO);
}
