package org.tutorials.servletdemo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DemoListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    System.out.println("Context init");
    servletContextEvent.getServletContext().setAttribute("name", "jun");
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    System.out.println("contextDestroyed");
  }
}
