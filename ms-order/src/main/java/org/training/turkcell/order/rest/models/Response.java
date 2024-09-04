package org.training.turkcell.order.rest.models;

import lombok.Data;

@Data
public class Response<T> {
    private boolean errorOccured;
    private String error;
    private T response;
}
