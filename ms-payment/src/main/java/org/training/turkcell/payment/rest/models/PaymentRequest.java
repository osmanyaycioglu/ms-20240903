package org.training.turkcell.payment.rest.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String     orderId;
    private BigDecimal amount;
    private Long       customerId;
    private String     customerNumber;
}
