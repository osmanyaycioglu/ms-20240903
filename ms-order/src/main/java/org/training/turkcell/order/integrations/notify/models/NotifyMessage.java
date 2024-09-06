package org.training.turkcell.order.integrations.notify.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifyMessage {
    private String dest;
    private String msg;
}
