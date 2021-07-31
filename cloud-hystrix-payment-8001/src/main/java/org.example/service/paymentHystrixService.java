package org.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

        int number = 5;
        try {
            TimeUnit.SECONDS.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "getPaymentId_out id :" + id;
    }

    public String getPaymentId_TimeOut_fallbackhandlar(Integer id){
        return "线程池:" + Thread.currentThread().getName() + "getPaymentId_out fallback :";
    }
}
