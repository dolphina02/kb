package com.kb.shop;

import com.kb.shop.domain.Product;
import com.kb.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product();
        product1.setName("Sample Product 1");
        product1.setDescription("This is a description for sample product 1.");
        product1.setPrice(19.99);
        product1.setImageUrl("https://via.placeholder.com/150");

        Product product2 = new Product();
        product2.setName("Sample Product 2");
        product2.setDescription("This is a description for sample product 2.");
        product2.setPrice(29.99);
        product2.setImageUrl("https://via.placeholder.com/150");

        productRepository.save(product1);
        productRepository.save(product2);
    }
}
