package org.example.controller;

import org.example.entities.CommonResult;
import org.example.entities.Payment;
import org.example.service.FeginOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeginOrderController {


    @Resource
    private FeginOrderService feginOrderService;

    @GetMapping("/consumer/get/{id}")
    public CommonResult<Payment> getPayment (@PathVariable("id") Long id){
        return feginOrderService.getPayment(id);
    }


    @GetMapping(value = "/consumer/timeOut")
    public String getPaymentTO(){
        return feginOrderService.getPaymentTO();
    }
}
