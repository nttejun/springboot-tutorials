package org.batch.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.TimeZone;

public class JobScheduler {

    public static void main(String... args){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+0900"));
        new ClassPathXmlApplicationContext("classpath:applicationContext-batch.xml");
    }
}
