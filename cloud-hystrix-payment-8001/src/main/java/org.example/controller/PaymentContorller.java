package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.entities.CommonResult;
import org.example.entities.Payment;
import org.example.service.PaymentService;
import org.example.service.paymentHystrixService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentContorller {

    @Resource
    private paymentHystrixService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping(value = "get/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String service :services){
            log.info("******service******"+service+"\t");
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance:instances){

            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getUri()+"\t");
        }
        return discoveryClient;

    }

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String getPaymentHystrixOk(@PathVariable("id") Integer id){
        return paymentService.getPaymentId_Ok(id);
    }



    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String getPaymentHystrixTimeOut(@PathVariable("id") Integer id){
        return paymentService.getPaymentId_TimeOut(id);
    }

}
