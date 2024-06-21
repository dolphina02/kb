package com.kb.shop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass // 이 참조를 넣어줘야 product entity에서 공통속성에 존재하는 attr 을 jpa에서 인식, ProductBase는 Entity 가 아님을 명시
public abstract class ProductBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl; // 이미지 URL 필드 추가


}
