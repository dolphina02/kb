// CartItemRepository.java
package com.kb.shop.repository;

import com.kb.shop.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
