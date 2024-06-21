// PaymentService.java
package com.kb.shop.service;

import com.kb.shop.domain.Payment;
import com.kb.shop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(String paymentMethod, double amount) {
        Payment payment = new Payment();
        payment.setPaymentMethod(paymentMethod);
        payment.setAmount(amount);
        return paymentRepository.save(payment);
    }
}
