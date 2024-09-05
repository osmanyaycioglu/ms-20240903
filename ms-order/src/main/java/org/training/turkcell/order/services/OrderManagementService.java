package org.training.turkcell.order.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.turkcell.order.services.models.Order;

@Service
public class OrderManagementService {

    public String placeOrder(Order orderParam){
        return null;
    }

    public String cancelOrder(@RequestParam String orderId) {
        return null;
    }


    public String reactivateOrder(@RequestParam String orderId) {
        return null;
    }


}
