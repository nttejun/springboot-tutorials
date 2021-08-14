package com.tutorial.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ProxySimpleEventService implements EventService {

  @Autowired
  SimpleEventService simpleEventService;

  @Override
  public void createEvent() {
    long begin = System.currentTimeMillis();
    simpleEventService.createEvent();
    System.out.println("프록시 빈 사용예시");
    System.out.println(System.currentTimeMillis() - begin);
  }

  @Override
  public void publishEvent() {
    long begin = System.currentTimeMillis();
    simpleEventService.publishEvent();
    System.out.println("프록시 빈 사용예시");
    System.out.println(System.currentTimeMillis() - begin);
  }

  @Override
  public void deleteEvent() {
    long begin = System.currentTimeMillis();
    simpleEventService.deleteEvent();
    System.out.println("프록시 빈 사용예시");
    System.out.println(System.currentTimeMillis() - begin);
  }

}
