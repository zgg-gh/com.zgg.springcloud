package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.entities.CommonResult;
import org.example.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface FeginOrderService {

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment (@PathVariable("id") Long id);

    @GetMapping(value = "/payment/timeOut")
    public String getPaymentTO();
}
