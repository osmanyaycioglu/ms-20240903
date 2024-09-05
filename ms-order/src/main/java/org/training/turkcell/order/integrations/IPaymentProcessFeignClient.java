package org.training.turkcell.order.integrations;

import org.springframework.cloud.openfeign.FeignClient;
import org.training.turkcell.payment.rest.IPaymentProcessController;

@FeignClient(value = "MS-PAYMENT",contextId = "1")
public interface IPaymentProcessFeignClient extends IPaymentProcessController {

}
