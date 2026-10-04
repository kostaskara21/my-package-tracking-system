package com.kara.tracking.system.scheduler;

import com.github.kagkarlsson.scheduler.task.schedule.CronSchedule;
import com.github.kagkarlsson.scheduler.task.schedule.Schedule;
import com.kara.tracking.system.exceptions.SchedulerException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ScheduleTaskUtil {

    public static Schedule createCronSchedule(String cron){
     try {
         log.info("Creating schedule according to \"{}\" Cron expression",cron);
         return new CronSchedule(cron);
     }catch (IllegalArgumentException e){
         throw new SchedulerException("Unable to create a cron expression: "+ cron,e);
     }
    }
}
