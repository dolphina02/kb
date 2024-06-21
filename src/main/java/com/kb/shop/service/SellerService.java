package com.kb.shop.service;

import com.kb.shop.domain.Seller;
import com.kb.shop.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public Seller getSellerInfo (Long id) {
        return sellerRepository.selectSellerInfo(id);
    }

    public void deleteSellerInfo (Long id) {
        sellerRepository.deleteSellerInfo(id);
    }


    public Seller setSellerInfo (Seller seller) {
        sellerRepository.setSellerInfo(seller);
        //return getSellerInfo(seller.getId());
        return sellerRepository.selectSelectInfoBySellerName(seller.getSellerName());
    }
}
