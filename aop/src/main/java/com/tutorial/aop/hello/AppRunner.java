package com.tutorial.aop.hello;

import java.lang.reflect.Proxy;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments args) throws Exception {
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
