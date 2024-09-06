package org.training.turkcell.order.resilience;

import org.training.turkcell.payment.rest.models.PaymentResponse;

import java.util.function.Predicate;

public class PaymentProcessResultRetryPredicate implements Predicate<PaymentResponse> {
    @Override
    public boolean test(final PaymentResponse paymentResponseParam) {
        if (paymentResponseParam.getPaymentId() == null) {
            return true;
        }
        return false;
    }
}
