package com.kb.shop.controller;

import com.kb.shop.domain.Promotion;
import com.kb.shop.domain.Seller;
import com.kb.shop.service.PromotionService;
import com.kb.shop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerInfo(@PathVariable Long id) {
        Seller seller = new Seller();
        seller = sellerService.getSellerInfo(id);

        if (seller != null) {
            return new ResponseEntity<>(seller, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePromotionInfo(@PathVariable Long id) {
        sellerService.deleteSellerInfo(id);
    }

    @PostMapping("/")
    public ResponseEntity<Seller> setSellerInfo(@RequestBody Seller seller) {
        Seller newSeller = new Seller();
        newSeller = sellerService.setSellerInfo(seller);
        if (newSeller != null) {
            return new ResponseEntity<>(newSeller, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
