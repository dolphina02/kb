package com.kb.shop.controller;

import com.kb.shop.domain.Product;
import com.kb.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("cart")
public class PaymentController {

    @Autowired
    private ProductService productService;

    @ModelAttribute("cart")
    public List<Product> cart() {
        return new ArrayList<>();
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam(required = false) Long productId, @ModelAttribute("cart") List<Product> cart, Model model) {
        if (productId != null) {
            Product product = productService.findById(productId);
            if (product != null) {
                model.addAttribute("products", List.of(product));
                model.addAttribute("fromCart", false); // "Buy It Now" 경우
            }
        } else {
            model.addAttribute("products", cart);
            model.addAttribute("fromCart", true); // 장바구니에서 결제하는 경우
        }
        return "checkout";
    }

    @GetMapping("/complete")
    public String complete(@RequestParam boolean fromCart, HttpSession session, Model model) {
        if (fromCart) {
            session.removeAttribute("cart");  // Clear the cart after payment completion from cart
            model.addAttribute("cart", new ArrayList<>());  // Reset cart to a new empty list
        }
        return "complete";
    }
}
