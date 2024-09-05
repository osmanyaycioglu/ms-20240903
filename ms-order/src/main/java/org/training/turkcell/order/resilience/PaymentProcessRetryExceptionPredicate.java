package org.training.turkcell.order.resilience;

import org.springframework.web.client.RestClientResponseException;
import org.training.turkcell.order.error.ErrorObj;

import java.io.IOException;
import java.util.function.Predicate;

public class PaymentProcessRetryExceptionPredicate implements Predicate<Throwable> {
    @Override
    public boolean test(final Throwable throwableParam) {
        if (throwableParam instanceof NullPointerException) {
            return false;
        } else if (throwableParam instanceof RestClientResponseException) {
            RestClientResponseException responseExceptionLoc = (RestClientResponseException) throwableParam;
            ErrorObj                    responseBodyAsLoc    = responseExceptionLoc.getResponseBodyAs(ErrorObj.class);
            switch (responseBodyAsLoc.getErrorCode()) {
                case 1024:
                    return true;
                case 2004:
                    return false;
                default:
                    return true;
            }
        } else if (throwableParam instanceof IOException) {
            return true;
        }
        return false;
    }
}
