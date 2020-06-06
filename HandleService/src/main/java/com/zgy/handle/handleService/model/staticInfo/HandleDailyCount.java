package com.zgy.handle.handleService.model.staticInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.handleService.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "handle_static_day")
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class HandleDailyCount extends BaseModel {
    private LocalDate recordDate;
    private Integer year;
    private Integer month;
    private Integer day; // 当天
    private Integer dailyCount;
    @Enumerated(EnumType.STRING)
    private HandleType handleType;

}
