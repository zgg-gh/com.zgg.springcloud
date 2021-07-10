package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entities.CommonResult;
import org.example.entities.Payment;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentContorller {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/add")
    public CommonResult add(@RequestBody Payment payment){
        int result = paymentService.add(payment);
        log.info("insert******:"+result);
        if(result>0){
            return new CommonResult(200,"成功",result);
        }else{

            return new CommonResult(500,"错误: ",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("insert******:123"+payment);
        if(payment != null){
            return new CommonResult(200,"成功",payment);
        }else{

            return new CommonResult(500,"错误: "+id,null);
        }
    }
}
