package com.kb.shop.controller;

import com.kb.shop.domain.Product;
import com.kb.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/") // localhost:8080/
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        return "home"; // home.html 템플릿 반환
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product/view";
        } else {
            return "error/404";
        }
    }
}
