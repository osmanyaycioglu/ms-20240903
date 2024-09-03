package org.training.turkcell.order.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order/management")
public class OrderManagementController {

    @PostMapping("/place")
    public void placeOrder(){
    }

    @PostMapping("/cancel")
    public void cancelOrder(){
    }

}
