package org.training.turkcell.order.integrations.notify;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.training.turkcell.order.integrations.notify.models.NotifyMessage;

@Service
@RequiredArgsConstructor
public class NotifyIntegration {
    private final RabbitTemplate rabbitTemplate;


    public void method(String dest,
                       String msg) {
        rabbitTemplate.convertAndSend("notify-topic-exchange",
                                      "eu.tr.message.sms.notify.p1",
                                      new NotifyMessage(dest,
                                                        msg));
    }

}
