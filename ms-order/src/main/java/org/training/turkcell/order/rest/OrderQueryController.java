package org.training.turkcell.order.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.turkcell.order.rest.models.OrderDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order/query")
public class OrderQueryController {

    @GetMapping("/get")
    public OrderDto getOrder(@RequestParam String orderId){
        return null;
    }

    @GetMapping("/search/active")
    public List<OrderDto> searchActiveOrders(){
        return null;
    }

}
