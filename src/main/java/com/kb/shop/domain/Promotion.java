package com.kb.shop.domain;

import com.kb.shop.domain.enums.PromotionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //1,2,3,4
    private String promotionName; // 상반기 전자제품 페스티벌
    private String promotionDescription; // 전자제품을 할인하는 페스티벌
    // 시작, 종료기간
    private LocalDateTime promotionStartDt;
    private LocalDateTime promotionEndDt;

    @Enumerated(EnumType.STRING)
    private PromotionType promotionType;

    private String affectRange; // 범위, Category, ProductCode
    private double discountRate; // 할인, 20%

}
