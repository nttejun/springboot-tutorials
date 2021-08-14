package com.tutorial.aop;

import org.springframework.stereotype.Service;

@Service
public class SimpleEventService implements EventService{

  @Override
  public void createEvent() {
    long begin = System.currentTimeMillis();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("create");
    System.out.println(System.currentTimeMillis() - begin);
  }

  @Override
  public void publishEvent() {
    long begin = System.currentTimeMillis();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("publish");
    System.out.println(System.currentTimeMillis() - begin);
  }
}
