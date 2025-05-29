package com.example.foodtracker.Controller;

import com.example.foodtracker.Repository.UserRepository;
import com.example.foodtracker.domain.User;
import com.example.foodtracker.service.FoodLogService;
import com.example.foodtracker.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
public class FoodController {

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

    private final ProductService productService;
    //private final RecipeService recipeService;
    private final FoodLogService foodLogService;
    private final UserRepository userRepository;

    public FoodController(ProductService productService, FoodLogService foodLogService, UserRepository userRepository) {
        this.productService = productService;
        //this.recipeService = recipeService;
        this.foodLogService = foodLogService;
        this.userRepository = userRepository;
    }

//    @GetMapping("/searchProduct")
//    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam String query) {
//        Map<String, Object> results = productService.searchProducts(query);
//        return ResponseEntity.ok(results);
//    }
//
//    @GetMapping("/getNutrition")
//    public ResponseEntity<Map<String, Object>> getProductDetails(@RequestParam String fdcId) {
//        Map<String, Object> details = productService.getProductDetails(fdcId);
//        return ResponseEntity.ok(details);
//    }

    @GetMapping("/searchFat")
    public ResponseEntity<Map<String, Object>> searchFats(@RequestParam String query) {
        Map<String, Object> response = productService.searchFat(query);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getFatNutrition")
    public ResponseEntity<Map<String, Object>> getFatNutrition(@RequestParam int foodId) {
        Map<String, Object> response = productService.getFatNutrition(foodId);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/log")
    public ResponseEntity<Map<String, Object>> logFood(
            @RequestBody Map<String, Object> payload,
            Principal principal                      // only need this
    ) {
        logger.info("logFood payload: {}", payload);
        logger.info("payload keys: {}", payload.keySet());

        String username = principal.getName();   // "test4@gmail.com"

        // 2) Look up your domain User via the injected repository instance
        User userDetails = userRepository
                .getUserByEmail(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException(username);
        }

        int userId = userDetails.getId();       // now non-null

        // 3) Parse the payload
        int foodId = Integer.parseInt(payload.get("foodId").toString());
        String mealType = payload.get("mealType").toString();

        // 4) Delegate to your service
        foodLogService.logFood(foodId, mealType, userId);

        // 5) Return 200 OK
        return ResponseEntity.ok(Map.of("status", "success"));
    }
}

