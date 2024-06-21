// PaymentRepository.java
package com.kb.shop.repository;

import com.kb.shop.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
