package org.training.turkcell.order.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

//@Component
@RequiredArgsConstructor
public class MyTestStarter implements ApplicationRunner {
    private final MyBean                 myBean;
    private final RetryRegistry          retryRegistry;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        CircuitBreaker circuitBreakerLoc = circuitBreakerRegistry.circuitBreaker("payment-process-cb");
        circuitBreakerLoc.getEventPublisher()
                         .onStateTransition(st -> System.out.println("***** state changed : "
                                                                     + st.getStateTransition()));
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception exp) {
            }


            try {
                myBean.callMe("+++++ initiate : " + i);
            } catch (Exception eParam) {
                System.out.println("Exception geldi : " + eParam.getClass()
                                                                .getName() + " msg : " + eParam.getMessage());
            }
            CircuitBreaker.Metrics locMetricsLoc = circuitBreakerLoc.getMetrics();

            System.out.println("**-" + circuitBreakerLoc.getState() + "**** CB metrics : failureRate : "
                               + locMetricsLoc.getFailureRate()
                               + " Success : "
                               + locMetricsLoc.getNumberOfSuccessfulCalls()
                               + " Not Permitted : "
                               + locMetricsLoc.getNumberOfNotPermittedCalls()
                               + " failed : "
                               + locMetricsLoc.getNumberOfFailedCalls()
            );
        }
    }

    public void run2(final ApplicationArguments args) throws Exception {
        String osmanLoc = myBean.test("osman");
        System.out.println("Aspect Code Test : " + osmanLoc);
        Set<Retry> allRetriesLoc = retryRegistry.getAllRetries();
        for (Retry allRetryLoc : allRetriesLoc) {
            System.out.println(" Config : " + allRetryLoc.getName());
        }

        Retry       retryLoc       = retryRegistry.retry("payment-process-retry");
        RetryConfig retryConfigLoc = retryLoc.getRetryConfig();
        System.out.println("Retry Local config : " + retryConfigLoc);
        retryLoc.getEventPublisher()
                .onRetry(r -> System.out.println("------------------------------ Retry initiated : " + r));
        for (int i = 0; i < 30; i++) {
            myBean.callMe("+++++ initiate : " + i);
            Retry.Metrics metricsLoc = retryLoc.getMetrics();
            System.out.println("**** Retry metrics : no_s : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " no_f : "
                               + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
                               + " no_s_r : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + " no_f_r : "
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt());
        }
    }

}
