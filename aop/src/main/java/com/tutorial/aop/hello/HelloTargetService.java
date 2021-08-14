package com.tutorial.aop.hello;

public class HelloTargetService implements Hello {

  @Override
  public String sayHello(String name) {
    return "Hello " + name;
  }

  @Override
  public String sayHi(String name) {
    return "Hi " + name;
  }

  @Override
  public String sayThankYou(String name) {
    return "Thank you " + name;
  }
}
