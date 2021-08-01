package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.example.service.HystrixOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class HystrixOrderController {

    @Resource
    HystrixOrderService hystrixOrderService;

    @GetMapping(value = "/hystrix/order/ok/{id}")
    public String getPaymentHystrixOk(@PathVariable("id") Integer id){
        return hystrixOrderService.getPaymentHystrixOk(id);
    }

//    @GetMapping(value = "/hystrix/order/timeout/{id}")
//    public String getPaymentHystrixTimeOut(@PathVariable("id") Integer id){
//        return hystrixOrderService.getPaymentHystrixTimeOut(id);
//    }

    @GetMapping(value = "/hystrix/order/timeout/{id}")
    @HystrixCommand(fallbackMethod = "getPaymentId_TimeOut_fallbackhandlar",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String getPaymentId_TimeOut(Integer id) {

        int number =5;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "getPaymentId_out id :" + id;
    }

    public String getPaymentId_TimeOut_fallbackhandlar(Integer id){
        return "线程池:" + Thread.currentThread().getName() + "系统繁忙，请稍后再试 fallback :";
    }
}
