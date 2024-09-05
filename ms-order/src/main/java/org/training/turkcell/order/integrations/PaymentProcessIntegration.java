package org.training.turkcell.order.integrations;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.training.turkcell.order.services.models.Order;
import org.training.turkcell.payment.rest.models.PaymentRequest;
import org.training.turkcell.payment.rest.models.PaymentResponse;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PaymentProcessIntegration {
    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;
    private final IPaymentProcessFeignClient  paymentProcessFeignClient;

    private AtomicLong index = new AtomicLong();


    public PaymentResponse pay(Order orderParam) {
        if (orderParam.getPrice() == null) {
            throw new IllegalArgumentException("Price bilgisi boş olamaz");
        }

        PaymentRequest paymentRequestLoc = PaymentRequest.builder()
                                                         .withAmountParam(orderParam.getPrice())
                                                         .withCustomerIdParam(orderParam.getCustomerId())
                                                         .withCustomerNumberParam(orderParam.getPhoneNumber())
                                                         .withOrderIdParam(orderParam.getOrderId())
                                                         .build();

        return paymentProcessFeignClient.pay(paymentRequestLoc);

    }

    public PaymentResponse pay2(Order orderParam) {
        if (orderParam.getPrice() == null) {
            throw new IllegalArgumentException("Price bilgisi boş olamaz");
        }

        PaymentRequest paymentRequestLoc = PaymentRequest.builder()
                                                         .withAmountParam(orderParam.getPrice())
                                                         .withCustomerIdParam(orderParam.getCustomerId())
                                                         .withCustomerNumberParam(orderParam.getPhoneNumber())
                                                         .withOrderIdParam(orderParam.getOrderId())
                                                         .build();

        return restTemplate.postForObject("http://MS-PAYMENT/api/v1/payment/process/pay",
                                          paymentRequestLoc,
                                          PaymentResponse.class);

    }


    public PaymentResponse pay3(Order orderParam) {
        if (orderParam.getPrice() == null) {
            throw new IllegalArgumentException("Price bilgisi boş olamaz");
        }

        PaymentRequest paymentRequestLoc = PaymentRequest.builder()
                                                         .withAmountParam(orderParam.getPrice())
                                                         .withCustomerIdParam(orderParam.getCustomerId())
                                                         .withCustomerNumberParam(orderParam.getPhoneNumber())
                                                         .withOrderIdParam(orderParam.getOrderId())
                                                         .build();
        RestTemplate       restTemplateLoc = new RestTemplate();
        Application        applicationsLoc = eurekaClient.getApplication("MS-PAYMENT");
        List<InstanceInfo> instancesLoc    = applicationsLoc.getInstances();
        int                currentIndex    = (int) (index.incrementAndGet() % instancesLoc.size());
        InstanceInfo       instanceInfoLoc = instancesLoc.get(currentIndex);
        return restTemplateLoc.postForObject("http://"
                                             + instanceInfoLoc.getIPAddr()
                                             + ":"
                                             + instanceInfoLoc.getPort()
                                             + "/api/v1/payment/process/pay",
                                             paymentRequestLoc,
                                             PaymentResponse.class);

    }

}
