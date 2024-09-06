package org.training.turkcell.order.resilience;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyBean {
    private long count = 0;

    @CircuitBreaker(name = "payment-process-cb", fallbackMethod = "callMeFallback")
    // @Retry(name = "payment-process-retry")
    public String callMe(String str) {
        System.out.println("Count : " + count + " str : " + str);
        count++;
        if (count < 30) {
            if (count % 3 == 0) {
                throw new IllegalArgumentException("count : " + count);
            }
        }
        return "Call : " + count + " success";
    }

    public String callMeFallback(String str,
                                 Throwable throwableParam) {
        System.out.println("-*-*-*-*-*-* Fallback : " + throwableParam);
        return "Fallback sonucu";
    }

    @MyInterceptAnno
    public String test(String str) {
        return "Hello world : " + str;
    }

}
