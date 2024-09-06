package org.training.turkcell.order.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.turkcell.order.integrations.PaymentProcessIntegration;
import org.training.turkcell.order.integrations.notify.NotifyIntegration;
import org.training.turkcell.order.services.models.Order;
import org.training.turkcell.payment.rest.models.PaymentResponse;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final PaymentProcessIntegration paymentProcessIntegration;
    private final NotifyIntegration         notifyIntegration;

    public String placeOrder(Order orderParam) {
        orderParam.setPrice(new BigDecimal(1000));
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        PaymentResponse payLoc = paymentProcessIntegration.pay(orderParam);
        orderParam.setPaymentId(payLoc.getPaymentId());
        // Data layer git ve yaz
        String msg = "Siparişiniz alındı sipariş no su : "
                     + orderParam.getOrderId()
                     + " ödeme id : "
                     + payLoc.getPaymentId()
                     + " server : "
                     + payLoc.getDesc();
        notifyIntegration.method(orderParam.getPhoneNumber(),
                                 msg);
        return msg;

    }

    public String cancelOrder(@RequestParam String orderId) {
        return null;
    }


    public String reactivateOrder(@RequestParam String orderId) {
        return null;
    }


}
