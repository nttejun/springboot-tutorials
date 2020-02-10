package org.batch.quartz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class AbstractTaskLetTest {
    @Autowired
    ApplicationContext applicationContext;
}
