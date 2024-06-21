package com.kb.shop.domain;

import com.kb.shop.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMethod;
    private double amount;
    private LocalDateTime paymentDate;
    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToOne(fetch = FetchType.LAZY) // 데이터는 실제로 접근할 때 로드됨, 데이터를 접근할 때마다 추가적인 데이터베이스 쿼리가 발생
    @JoinColumn(name = "order_id")
    private Order order;

    private String provider; // 외부 결제 제공자 (e.g., PayPal, Stripe, etc.)
    private String externalTransactionId; // 외부 결제 시스템의 트랜잭션 ID

    // Getters and setters
}
