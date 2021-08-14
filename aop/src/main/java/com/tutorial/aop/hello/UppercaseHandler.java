package com.tutorial.aop.hello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {
  Object target; // invoke return 타입은 Object 하나로 확장성을 위해 기존 Hello 에서 Object 타입으로 변경

  public UppercaseHandler(Hello target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String ret = (String)method.invoke(target, args);
    return ret.toUpperCase();
  }
}
