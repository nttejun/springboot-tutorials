package org.batch.quartz.count;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoubleCountJob implements Job {
    private Logger log = LoggerFactory.getLogger(DoubleCountJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("------------------DoubleCount Job Start------------------");
        for(int i=0; i<5; i++){
            log.info("DoubleCountJob");
        }
        log.info("------------------DoubleCount Job End------------------");
    }
}
