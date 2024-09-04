package org.training.turkcell.order.rest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.training.turkcell.order.rest.models.OrderDto;
import org.training.turkcell.order.rest.models.OrderPlaceResponse;

@RequestMapping("/api/v1/order/management")
public interface IOrderManagementController {
    @PostMapping("/place")
    @Operation(summary = "order yaratma", description = "kullanıcının order yaratması")
    OrderPlaceResponse placeOrder(@Valid @RequestBody OrderDto orderDtoParam);

    @GetMapping("/cancel")
    public String cancelOrder(@RequestParam String orderId);

    @GetMapping("/reactivate")
    public String reactivateOrder(@RequestParam String orderId);
}
