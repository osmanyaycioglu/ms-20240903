package org.training.turkcell.order.rest.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    @NotBlank
    @Size(min = 8,message = "phoneNumber min 8 olmalı")
    private String        phoneNumber;
    @NotEmpty
    private String        address;
    private String        street;
    @Valid
    private List<MealDto> meals;
    private LocalDateTime scheduleTime;
}
