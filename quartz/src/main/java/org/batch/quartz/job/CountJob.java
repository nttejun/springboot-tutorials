package org.batch.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountJob implements Job {

    private Logger log = LoggerFactory.getLogger(CountJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("------------------Count Job Start------------------");

        try {
            log.info(">>> Thread Sleep 10000");
            String threadName = Thread.currentThread().getName();
            Thread.sleep(10000);
            log.info(">>> Thread Name : " + threadName);
            log.info(">>> Thread Wake up 10000");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("------------------Count Job End------------------");
    }
}
