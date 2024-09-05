package org.training.turkcell.order.integrations;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessIntegration.class);

    private final RestTemplate               restTemplate;
    private final EurekaClient               eurekaClient;
    private final IPaymentProcessFeignClient paymentProcessFeignClient;

    private AtomicLong index = new AtomicLong();


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

        return paymentProcessFeignClient.pay(paymentRequestLoc);

    }
    @Retry(name = "payment-process-retry2")
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

    @Retry(name = "payment-process-retry")
    public PaymentResponse pay(Order orderParam) {
        if (orderParam.getPrice() == null) {
            throw new IllegalArgumentException("Price bilgisi boş olamaz");
        }
        if (orderParam.getPrice() != null) {
            throw new IllegalStateException("Price bilgisi boş olamaz");
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


        PaymentResponse paymentResponseLoc = restTemplateLoc.postForObject("http://"
                                                                           + instanceInfoLoc.getIPAddr()
                                                                           + ":"
                                                                           + instanceInfoLoc.getPort()
                                                                           + "/api/v1/payment/process/pay",
                                                                           paymentRequestLoc,
                                                                           PaymentResponse.class);

        return paymentResponseLoc;

    }

}
