package com.zgy.handle.handleService.repository.staticInfo;

import com.zgy.handle.handleService.model.staticInfo.HandleType;
import com.zgy.handle.handleService.model.staticInfo.MonthHandleTotal;
import com.zgy.handle.handleService.repository.SystemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthHandleTotalRepository extends SystemRepository<MonthHandleTotal> {
    Optional<MonthHandleTotal> findAllByHandleTypeAndYearAndMonth(HandleType handleType,Integer year,Integer month);

    List<MonthHandleTotal> findAllByHandleTypeAndYear(HandleType handleType,Integer year);
}
