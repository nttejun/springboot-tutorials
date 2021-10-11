package com.starter.book.controller;

import com.starter.book.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

  @Autowired
  EventService eventService;

  /***
   * GetMapping > RequestMapping > Mapping
   */
  @GetMapping(value = "/events")
  public String events(Model model) {
    model.addAttribute("events", eventService.getEvents());
    return "events";
  }
}
