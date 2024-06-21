package com.kb.shop.service;

import com.kb.shop.domain.CartItem;
import com.kb.shop.domain.Product;
import com.kb.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    private static final String CART_SESSION_KEY = "cart";

    public List<CartItem> getCartItems(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    public void addToCart(Long productId, int quantity, HttpSession session) {
        List<CartItem> cart = getCartItems(session);
        Product product = productRepository.findById(productId).orElseThrow();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.add(cartItem);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void removeFromCart(Long id, HttpSession session) {
        List<CartItem> cart = getCartItems(session);
        cart.removeIf(item -> item.getId().equals(id));
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
}
