package org.training.turkcell.order.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.training.turkcell.order.rest.mappers.IOrderMappings;
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
        String orderId = orderManagementService.placeOrder(IOrderMappings.ORDER_MAPPINGS.toOrder(orderDtoParam));

        return OrderPlaceResponse.builder()
                                 .withOrderId(orderId)
                                 .withEstimation(LocalDateTime.now()
                                                              .plusHours(1))
                                 .build();
    }

    public String cancelOrder(@RequestParam String orderId) {
        return orderManagementService.cancelOrder(orderId);
    }


    public String reactivateOrder(@RequestParam String orderId) {
        return orderManagementService.reactivateOrder(orderId);
    }

}
