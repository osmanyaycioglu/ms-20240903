package org.training.turkcell.payment.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.turkcell.payment.rest.models.PaymentRequest;
import org.training.turkcell.payment.rest.models.PaymentResponse;

@RestController
@RequestMapping("/api/v1/payment/process")
public class PaymentProcessController {

    @Value("${server.port}")
    private Integer port;

    @PostMapping("/pay")
    public PaymentResponse pay(PaymentRequest paymentRequestParam) {
        return new PaymentResponse(paymentRequestParam.getCustomerNumber(),
                                   paymentRequestParam.getOrderId(),
                                   "port : " + port);
    }

}
