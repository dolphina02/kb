package com.kb.shop.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ShippingInfo {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    // Other fields, constructors, getters and setters
}
