package com.tutorial.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // Bean으로 등록
@Aspect // Aspect 라는 것을 명시
public class PerfAspect  {

  @Around("execution(* com.tutorial.*.EventService.*(..))")
  public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
    long begin = System.currentTimeMillis();
    Object retVal = pjp.proceed();
    System.out.println("Spring AOP 사용예시");
    System.out.println(System.currentTimeMillis() - begin);
    return retVal;
  }

}
