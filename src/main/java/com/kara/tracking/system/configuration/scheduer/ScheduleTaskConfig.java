package com.kara.tracking.system.configuration.scheduer;

import com.github.kagkarlsson.scheduler.Scheduler;
import com.github.kagkarlsson.scheduler.task.Task;
import com.github.kagkarlsson.scheduler.task.helper.RecurringTask;
import com.github.kagkarlsson.scheduler.task.helper.Tasks;
import com.github.kagkarlsson.scheduler.task.schedule.Schedule;
import com.github.kagkarlsson.scheduler.task.schedule.Schedules;
import com.kara.tracking.system.scheduler.ScheduleTaskUtil;
import com.kara.tracking.system.service.ProcessingPackageDeliveredEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "spring.scheduler.EmailNotify.enabled",
        havingValue = "true"
)
@Slf4j
public class ScheduleTaskConfig {

    private static final String EMAIL_NOTIFICATION ="Send_Arrived_Email";


    @Bean
    RecurringTask<Void> createTask(@Value("${spring.scheduler.EmailNotify.cron}")
                                   String cronExpression,
                                   ProcessingPackageDeliveredEmailService processingPackageDeliveredEmailService) {

        log.info("Bean Scheduler Created");
        Schedule schedule = ScheduleTaskUtil.createCronSchedule(cronExpression);

        return Tasks.recurring(EMAIL_NOTIFICATION, schedule)
                .execute((inst, ctx) -> {
                    log.info("Running Scheduled Task: {}",inst.getTaskName());
                    processingPackageDeliveredEmailService.processPackageDeliveredEmail();
                });
    }

}
