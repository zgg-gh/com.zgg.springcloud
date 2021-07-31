package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.entities.CommonResult;
import org.example.entities.Payment;
import org.example.service.PaymentService;
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
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/add")
    public CommonResult add(@RequestBody Payment payment){
        int result = paymentService.add(payment);
        log.info("insert******:"+result);
        if(result>0){
            return new CommonResult(200,"成功"+serverPort,result);
        }else{

            return new CommonResult(500,"错误: "+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("insert******:123"+payment);
        if(payment != null){
            return new CommonResult(200,"成功"+serverPort,payment);
        }else{

            return new CommonResult(500,"错误: "+serverPort+id,null);
        }
    }

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

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }



    @GetMapping(value = "/payment/timeOut")
    public String getPaymentTO(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
