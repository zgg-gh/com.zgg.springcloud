package org.example.controller;

import org.example.service.HystrixOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HystrixOrderController {

    @Resource
    HystrixOrderService hystrixOrderService;

    @GetMapping(value = "/hystrix/order/ok/{id}")
    public String getPaymentHystrixOk(@PathVariable("id") Integer id){
        return hystrixOrderService.getPaymentHystrixOk(id);
    }

    @GetMapping(value = "/hystrix/order/timeout/{id}")
    public String getPaymentHystrixTimeOut(@PathVariable("id") Integer id){
        return hystrixOrderService.getPaymentHystrixTimeOut(id);
    }
}
