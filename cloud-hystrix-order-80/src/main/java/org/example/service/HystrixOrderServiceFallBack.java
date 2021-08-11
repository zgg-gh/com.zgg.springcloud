package org.example.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HystrixOrderServiceFallBack implements HystrixOrderService{

    @Override
    public String getPaymentHystrixOk(Integer id) {
        return "getPaymentHystrixOk 稍后请重试";
    }

    @Override
    @Transactional
    public String getPaymentHystrixTimeOut(Integer id) {
        return "getPaymentHystrixTimeOut 稍后请重试";
    }
}
