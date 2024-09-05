package org.training.turkcell.order.resilience;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Around("@annotation(myInterceptAnnoParam)")
    public Object method(ProceedingJoinPoint ppp,
                       MyInterceptAnno myInterceptAnnoParam) {

        try {
            Object proceedLoc = ppp.proceed();
            if (proceedLoc instanceof String) {
                return proceedLoc + " * intercepted.";
            }
        } catch (Throwable exp) {
            exp.printStackTrace();
        }
        return null;
    }

}
