package org.training.turkcell.order.rest.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderPlaceResponse {
    private String orderId;
    private LocalDateTime estimation;

    public OrderPlaceResponse() {
    }

    @Builder(setterPrefix = "with")
    public OrderPlaceResponse(final String orderId,
                              final LocalDateTime estimation) {
        this.orderId    = orderId;
        this.estimation = estimation;
    }
}
