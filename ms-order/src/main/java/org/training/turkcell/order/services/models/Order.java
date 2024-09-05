package org.training.turkcell.order.services.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.training.turkcell.order.rest.models.MealDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private String        phoneNumber;
    private String        address;
    private String        street;
    private List<Meal> meals;
    private LocalDateTime scheduleTime;
    private EOrderStatus  orderStatus;
    private Long          customerId;
}
