package org.training.turkcell.order.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.turkcell.order.integrations.PaymentProcessIntegration;
import org.training.turkcell.order.services.models.Order;
import org.training.turkcell.payment.rest.models.PaymentResponse;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final PaymentProcessIntegration paymentProcessIntegration;

    public String placeOrder(Order orderParam) {
        orderParam.setPrice(new BigDecimal(1000));
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        PaymentResponse payLoc = paymentProcessIntegration.pay(orderParam);
        orderParam.setPaymentId(payLoc.getPaymentId());
        // Data layer git ve yaz
        return "Siparişiniz alındı sipariş no su : "
               + orderParam.getOrderId()
               + " ödeme id : "
               + payLoc.getPaymentId()
               + " server : "
               + payLoc.getDesc();
    }

    public String cancelOrder(@RequestParam String orderId) {
        return null;
    }


    public String reactivateOrder(@RequestParam String orderId) {
        return null;
    }


}
