package com.zgy.handle.handleService.model.staticInfo;

import com.zgy.handle.handleService.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "handle_handle_daily_count")
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class HandleDailyCount extends BaseModel {
    private LocalDate recordDate;
    private Integer dailyCount;
    private HandleType handleType;
}
