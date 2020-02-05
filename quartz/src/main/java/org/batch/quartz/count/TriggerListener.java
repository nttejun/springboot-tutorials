package org.batch.quartz.count;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriggerListener implements org.quartz.TriggerListener {

    private final Logger log = LoggerFactory.getLogger(TriggerListener.class);

    @Override
    public String getName() {
        return TriggerListener.class.getSimpleName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        log.info("{} trigger is fired", getName());
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        log.info("{} was about to be executed but a TriggerListener vetoed is's execution", context.getJobDetail().getKey().toString());
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("{} trigger was misfired", getName());
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        log.info("{} trigger is complete", getName());
    }
}
