package com.example.foodtracker.Controller;

import com.example.foodtracker.service.ProductService;
import com.example.foodtracker.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class FoodController {

    private final ProductService productService;
    private final RecipeService recipeService;

    public FoodController(ProductService productService, RecipeService recipeService) {
        this.productService = productService;
        this.recipeService = recipeService;
    }

    @GetMapping("/searchProduct")
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam String query) {
        Map<String, Object> results = productService.searchProducts(query);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/getNutrition")
    public ResponseEntity<Map<String, Object>> getProductDetails(@RequestParam String fdcId) {
        Map<String, Object> details = productService.getProductDetails(fdcId);
        return ResponseEntity.ok(details);
    }
}
