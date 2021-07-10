package org.example.service.impl;

import org.example.dao.PaymentDao;
import org.example.entities.Payment;
import org.example.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao ;

    @Override
    public  int add(Payment payment){
        return  paymentDao.add(payment);

    }
    @Override
    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}
