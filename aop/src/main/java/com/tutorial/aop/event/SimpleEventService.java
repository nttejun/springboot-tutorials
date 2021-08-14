package com.tutorial.aop.event;

import org.springframework.stereotype.Service;

@Service
public class SimpleEventService implements EventService{

  @Override
  public void createEvent() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("create");
  }

  @Override
  public void publishEvent() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("publish");
  }

  public void deleteEvent() {
    System.out.println("delete");
  }
}
