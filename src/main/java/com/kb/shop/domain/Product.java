package com.kb.shop.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends ProductBase {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private String description;
//    private double price;
//    private String imageUrl; // 이미지 URL 필드 추가
    private Integer qty;
    private String productCategory;
    private String vendorCode;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stock> stocks;



}
