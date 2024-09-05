package org.training.turkcell.payment.rest.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String     orderId;
    private BigDecimal amount;
    private Long       customerId;
    private String     customerNumber;

    public PaymentRequest() {
    }

    @Builder(setterPrefix = "with")
    public PaymentRequest(final String orderIdParam,
                          final BigDecimal amountParam,
                          final Long customerIdParam,
                          final String customerNumberParam) {
        orderId        = orderIdParam;
        amount         = amountParam;
        customerId     = customerIdParam;
        customerNumber = customerNumberParam;
    }
}
