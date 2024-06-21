package com.kb.shop.service;

import com.kb.shop.domain.Promotion;
//import com.kb.shop.repository.PromotionAsyncRepository;
import com.kb.shop.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public Optional<Promotion> getPromotionInfo(Long id) {
        return promotionRepository.findById(id);
    }

    public void deletePromotionInfo(Long id) {
        promotionRepository.deleteById(id);
    }

    public Optional<Promotion> setPromotionInfo(Promotion promotion) {
        promotionRepository.save(promotion);
        return promotionRepository.findById(promotion.getId());
    }
//
//    @Autowired
//    private PromotionAsyncRepository promotionAsyncRepository;
//
//    public Mono<Promotion> getPromotion(Long id) {
//        return promotionAsyncRepository.findById(id);
//    }
//
//    public Flux<Promotion> getAllPromotions() {
//        return promotionAsyncRepository.findAll();
//    }
//
//    public Mono<Promotion> createPromotion(Promotion promotion) {
//        return promotionAsyncRepository.save(promotion);
//    }
//
//    public Mono<Void> deletePromotion(Long id) {
//        return promotionAsyncRepository.deleteById(id);
//    }



//    // 임의의 프로모션 데이터 추가
//    public void generateRandomPromotion() {
//        Promotion promotion = new Promotion();
//        promotion.setPromotionName("Random Promotion " + new Random().nextInt(1000));
//        promotion.setPromotionDescription("Description for promotion");
//        promotion.setPromotionStartDt(LocalDateTime.now());
//        promotion.setPromotionEndDt(LocalDateTime.now().plusDays(10));
//        promotion.setPromotionType(PromotionType.SALE);
//        promotion.setAffectRange("Category");
//        promotion.setDiscountRate(new Random().nextDouble() * 100);
//
//        createPromotion(promotion).subscribe();
//    }

}
