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
        getClass().getClassLoader(), // 동적으로 생성되는 다이내믹 프록시 클래스의 로딩에 사용할 클래스 로더
        new Class[]{Hello.class}, // 구현할 인터페이스
        new UppercaseHandler(new HelloTargetService()) //부가기능과 위임 관련 코드를 담고 있는 invocationHandler 구현 오브젝트를 제공해야 한다
    );
    System.out.println(proxiedHello.sayHello("jun"));
    System.out.println(proxiedHello.sayHi("jun"));
    System.out.println(proxiedHello.sayThankYou("jun"));
  }
}
