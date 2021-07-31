package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entities.Payment;

@Mapper
public interface PaymentDao {

    public int add(Payment payment) ;
    public Payment getPaymentById(@Param("id") Long id);
}
