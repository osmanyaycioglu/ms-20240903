package org.training.turkcell.order.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.training.turkcell.order.rest.models.OrderDto;
import org.training.turkcell.order.rest.models.OrderPlaceResponse;
import org.training.turkcell.order.services.OrderManagementService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderManagementController implements IOrderManagementController {
    private final OrderManagementService orderManagementService;


    public OrderPlaceResponse placeOrder(@Valid @RequestBody OrderDto orderDtoParam) {
        String orderId = orderManagementService.placeOrder(null);

        return OrderPlaceResponse.builder()
                                 .withOrderId("37246udhsf")
                                 .withEstimation(LocalDateTime.now()
                                                              .plusHours(1))
                                 .build();
    }

    public String cancelOrder(@RequestParam String orderId) {
        return null;
    }


    public String reactivateOrder(@RequestParam String orderId) {
        return null;
    }

}
