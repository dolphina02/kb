package com.kb.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Tag(name = "New Cart Controller", description = "Operations pertaining to shopping cart")
public class NewCartController {

    @GetMapping("/sampleApi")
    @Operation(summary = "Add a product to the cart")
    public String sampleApi (@RequestParam String returnValue, Integer valueA, Integer valueB) {
        return returnValue + " : " + String.valueOf(valueA + valueB);
    }
}
