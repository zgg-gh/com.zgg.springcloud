package org.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class paymentHystrixService {

    @Value("${server.port}")
    private String serverPort;

    public String getId() {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    public String getPaymentId_Ok(Integer id) {

        return "线程池:" + Thread.currentThread().getName() + "getPaymentId_Ok id :" + id;
    }


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

    @HystrixCommand(fallbackMethod = "getPaymentIdHystrixCircuitHandlar",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String getPaymentIdHystrixCircuit(Integer id) {

        if(id<0){
            throw new RuntimeException("*********ID 不能为负数************");
        }
        String serialNumber = UUID.randomUUID().toString();
        return "线程池:" + Thread.currentThread().getName() + "getPaymentId_out id :" + serialNumber;
    }

    public String getPaymentIdHystrixCircuitHandlar(Integer id){
        return "线程池:" + Thread.currentThread().getName() + "不能为负数，请稍后再试 fallback :";
    }
}
