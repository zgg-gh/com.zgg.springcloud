package org.example.service;

import org.apache.ibatis.annotations.Param;
import org.example.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    public int add(Payment payment) ;
    public Payment getPaymentById(@Param("id") Long id);
}
