package com.zgy.handle.tradingservice.mapper;

import com.zgy.handle.tradingservice.dto.TrackingDTO;
import com.zgy.handle.tradingservice.model.Tracking;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author: a4423
 * @date: 2021/4/9 22:35
 */
@Mapper(componentModel = "spring")
public interface TrackingMapper {
    Tracking toTracking(TrackingDTO trackingDTO);
    TrackingDTO toTrackingDTO(Tracking tracking);
    List<TrackingDTO> toTrackingDTOList(List<Tracking> trackingList);
}
