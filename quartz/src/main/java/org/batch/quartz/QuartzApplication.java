package org.batch.quartz;

import org.batch.quartz.count.CountScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzApplication {

    @Autowired
    private CountScheduler countScheduler;

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
