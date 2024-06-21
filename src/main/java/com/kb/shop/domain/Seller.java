package com.kb.shop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sellerName; // abcFashionMall
    @Enumerated(EnumType.STRING)
    private SellerGrade sellerGrade;
    private Double margin; // 3
    private String policy; // unlimit
    private String category; // fashion

    // insert into seller (id, sellerName, sellerGrade, margin, policy, category) values (1, 'abcFa.....);
}
