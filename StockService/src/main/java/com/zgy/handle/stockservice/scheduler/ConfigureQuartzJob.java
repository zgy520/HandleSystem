package com.zgy.handle.stockservice.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: a4423
 * @date: 2020/12/27 8:17
 */
@Configuration
public class ConfigureQuartzJob {
    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(SyncDailyStockJob.class)
                .withIdentity("syncDaily")
                .storeDurably().build();
    }
    @Bean
    public Trigger syncDailyStockJobTrigger(JobDetail jobDetail){
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity("syncStockTrigger")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 56 8 ? * * *"))
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 16 ? * * *"))
                .build();
    }
}
