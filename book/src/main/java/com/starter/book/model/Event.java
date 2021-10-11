package com.starter.book.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Event {

  private String name;

  private int  limitOfEnrollment;

  private LocalDateTime startDateTime;

  private LocalDateTime endDateTime;

}
