package org.batch.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobLauncherDetails extends QuartzJobBean {
    private JobLauncher jobLauncher;
    private Job job;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("jobKey", CommonUtil.getUUID());

        try{
            jobLauncher.run(job, jobParametersBuilder.toJobParameters());
        } catch (Exception e){
            throw new JobExecutionException(e.getMessage(), e);
        }

    }
}
