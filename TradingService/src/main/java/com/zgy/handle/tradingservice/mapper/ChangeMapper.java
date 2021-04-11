package com.zgy.handle.tradingservice.mapper;

import com.zgy.handle.tradingservice.dto.ChangeDTO;
import com.zgy.handle.tradingservice.model.Change;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author: a4423
 * @date: 2021/4/11 12:01
 */
@Mapper(componentModel = "spring")
public interface ChangeMapper {
    Change toChange(ChangeDTO changeDTO);
    ChangeDTO toChangeDTO(Change change);
    List<ChangeDTO> toChangeList(List<Change> changeList);
}
