package org.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-HYSTIRX-SERVICE")
public interface HystrixOrderService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String getPaymentHystrixOk(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String getPaymentHystrixTimeOut(@PathVariable("id") Integer id);
}
