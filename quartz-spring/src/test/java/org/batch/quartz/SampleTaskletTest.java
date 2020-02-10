package org.batch.quartz;


import org.batch.quartz.job.SampleTasklet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-batch.xml"})
public class SampleTaskletTest extends AbstractTaskLetTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void taskExecuteTest() throws Exception {
        SampleTasklet tasklet = applicationContext.getAutowireCapableBeanFactory().createBean(SampleTasklet.class);
        Assert.assertEquals(RepeatStatus.FINISHED, tasklet.execute(null, null));
    }
}