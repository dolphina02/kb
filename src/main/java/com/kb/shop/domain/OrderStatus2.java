package com.kb.shop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_status_2")
public class OrderStatus2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
