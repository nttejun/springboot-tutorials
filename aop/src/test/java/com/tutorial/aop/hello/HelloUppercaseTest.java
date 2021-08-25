package com.tutorial.aop.hello;

import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Test;

class HelloUppercaseTest {

  @Test
  void proxiedHello() {
    Hello proxiedHello = (Hello) Proxy.newProxyInstance(
        getClass().getClassLoader(),
        new Class[]{Hello.class},
        new UppercaseHandler(new HelloTargetService())
    );

    System.out.println(proxiedHello.sayHello("jun"));
    System.out.println(proxiedHello.sayHi("jun"));
    System.out.println(proxiedHello.sayThankYou("jun"));
  }
}