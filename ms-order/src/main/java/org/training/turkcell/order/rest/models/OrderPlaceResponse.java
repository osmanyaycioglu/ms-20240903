package org.training.turkcell.order.rest.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
public class OrderPlaceResponse {
    private String orderId;
    private LocalDateTime estimation;
    private String desc;

    public OrderPlaceResponse() {
    }

    @Builder(setterPrefix = "with")
    public OrderPlaceResponse(final String orderId,
                              final LocalDateTime estimation,
                              final String desc) {
        this.orderId    = orderId;
        this.estimation = estimation;
        this.desc = desc;
    }
}
