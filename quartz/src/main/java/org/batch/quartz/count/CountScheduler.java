package org.batch.quartz.count;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CountScheduler {
    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;

    @PostConstruct
    public void start() throws SchedulerException{
        schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        JobDetail doubleCountJob = JobBuilder.newJob(CountJob.class).withIdentity("countJob").build();
        Trigger doubleCountTrigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("1/5 * * * * ?")).build();

        JobDetail countJob = JobBuilder.newJob(DoubleCountJob.class).withIdentity("discountJob").build();
        Trigger countTrigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("1/10 * * * * ?")).build();

        scheduler.getListenerManager().addTriggerListener(new TriggerListener());
        scheduler.scheduleJob(doubleCountJob, doubleCountTrigger);
        scheduler.scheduleJob(countJob, countTrigger);
    }
}
