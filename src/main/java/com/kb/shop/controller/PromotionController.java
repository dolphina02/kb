package com.kb.shop.controller;

import com.kb.shop.domain.Promotion;
import com.kb.shop.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/promotion/{id}")
    public Optional<Promotion> getPromotionInfo(@PathVariable Long id) {
        return promotionService.getPromotionInfo(id);
    }

    @DeleteMapping("/promotion/{id}")
    public void deletePromotionInfo(@PathVariable Long id) {
        promotionService.deletePromotionInfo(id);
    }

    @PostMapping("/promotion")
    public ResponseEntity<Promotion> setPromotionInfo(@RequestParam Promotion promotion) {
        Optional<Promotion> newPromotion = promotionService.setPromotionInfo(promotion);
        if (newPromotion.isPresent()) {
            return ResponseEntity.ok(newPromotion.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
//
//    @GetMapping("/promo/async/{id}")
//    public Mono<ResponseEntity<Promotion>> getPromotion(@PathVariable Long id) {
//        return promotionService.getPromotion(id)
//                .map(promotion -> ResponseEntity.ok(promotion))
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/promo/async")
//    public Flux<Promotion> getAllPromotions() {
//        return promotionService.getAllPromotions();
//    }
//
//    @PostMapping("/promo/async")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Mono<Promotion> createPromotion(@RequestBody Promotion promotion) {
//        return promotionService.createPromotion(promotion);
//    }
//
//    @DeleteMapping("/promo/async/{id}")
//    public Mono<ResponseEntity<Void>> deletePromotion(@PathVariable Long id) {
//        return promotionService.deletePromotion(id)
//                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
//                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
}
