package com.starter.book.service;

import com.starter.book.model.Event;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  public List<Event> getEvents() {
    Event event1 = Event.builder()
        .name("MVC 스터디")
        .limitOfEnrollment(5)
        .startDateTime(LocalDateTime.of(2021, 9, 1, 10, 0))
        .endDateTime(LocalDateTime.of(2021, 9, 1, 12, 0))
        .build();

    Event event2 = Event.builder()
        .name("MVC 스터디")
        .limitOfEnrollment(5)
        .startDateTime(LocalDateTime.of(2021, 9, 1, 10, 0))
        .endDateTime(LocalDateTime.of(2021, 9, 1, 12, 0))
        .build();

    return com.sun.tools.javac.util.List.of(event1, event2);
  }
}
