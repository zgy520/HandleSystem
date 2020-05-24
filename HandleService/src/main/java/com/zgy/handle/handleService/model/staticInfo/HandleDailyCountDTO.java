package com.zgy.handle.handleService.model.staticInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class HandleDailyCountDTO {
    private LocalDate recordDate;
    private Integer dailyCount;
}
