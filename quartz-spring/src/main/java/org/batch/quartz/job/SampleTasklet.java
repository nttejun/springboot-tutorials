package org.batch.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class SampleTasklet implements Tasklet {
    private Logger log = LoggerFactory.getLogger(SampleTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
        log.info("------------Sample Tasklet Start");
        log.info("------------Sample Tasklet End");
        return RepeatStatus.FINISHED;
    }
}
