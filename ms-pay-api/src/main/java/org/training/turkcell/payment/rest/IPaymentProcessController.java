package org.training.turkcell.payment.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.training.turkcell.payment.rest.models.PaymentRequest;
import org.training.turkcell.payment.rest.models.PaymentResponse;

public interface IPaymentProcessController {
    @PostMapping("/api/v1/payment/process/pay")
    PaymentResponse pay(@RequestBody PaymentRequest paymentRequestParam);
}