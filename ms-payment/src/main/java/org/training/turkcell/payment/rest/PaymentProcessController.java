package org.training.turkcell.payment.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.turkcell.payment.rest.models.PaymentRequest;
import org.training.turkcell.payment.rest.models.PaymentResponse;

import java.util.UUID;

@RestController
public class PaymentProcessController implements  IPaymentProcessController{

    @Value("${server.port}")
    private Integer port;

    public PaymentResponse pay(@RequestBody PaymentRequest paymentRequestParam) {
        return new PaymentResponse(paymentRequestParam.getCustomerNumber(),
                                   paymentRequestParam.getOrderId(),
                                   "port : " + port,
                                   UUID.randomUUID()
                                       .toString());
    }

}
