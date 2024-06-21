package com.kb.shop.controller;

import com.kb.shop.domain.Product;
import com.kb.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;

@Controller
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private ProductService productService;

    @ModelAttribute("cart")
    public List<Product> cart() {
        return new ArrayList<>();  // 변경 가능한 리스트 반환
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute("cart") List<Product> cart) {
        Product product = productService.findById(id);
        if (product != null) {
            cart.add(product);
        }
        return "redirect:/";
    }

    @PostMapping("/cart/clear")
    public String clearCart(@ModelAttribute("cart") List<Product> cart) {
        cart.clear();
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(@ModelAttribute("cart") List<Product> cart, Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }
}
