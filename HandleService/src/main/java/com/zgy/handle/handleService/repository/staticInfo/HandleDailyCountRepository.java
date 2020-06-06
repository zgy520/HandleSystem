package com.zgy.handle.handleService.repository.staticInfo;

import com.zgy.handle.handleService.model.staticInfo.HandleDailyCount;
import com.zgy.handle.handleService.model.staticInfo.HandleType;
import com.zgy.handle.handleService.model.staticInfo.MonthHandleTotal;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HandleDailyCountRepository extends SystemRepository<HandleDailyCount> {

    Optional<HandleDailyCount> findByHandleTypeAndRecordDate(HandleType handleType, LocalDate recordDate);

    List<HandleDailyCount> findAllByHandleTypeAndYearAndMonth(HandleType handleType,Integer year,Integer month);

    //List<HandleDailyCount> findAllByHandleTypeAndYearAndMonthAndOrderByDayAsc(HandleType handleType, Integer year, Integer month);

}
