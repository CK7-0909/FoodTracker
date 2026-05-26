package com.example.foodtracker.Controller;

import com.example.foodtracker.Repository.UserRepository;
import com.example.foodtracker.Model.FoodLog;
import com.example.foodtracker.Model.User;
import com.example.foodtracker.service.FoodLogService;
import com.example.foodtracker.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class FoodController {

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

    private final ProductService productService;
    private final FoodLogService foodLogService;
    private final UserRepository userRepository;

    public FoodController(ProductService productService, FoodLogService foodLogService, UserRepository userRepository) {
        this.productService = productService;
        this.foodLogService = foodLogService;
        this.userRepository = userRepository;
    }

    @GetMapping("/searchFat")
    public ResponseEntity<Map<String, Object>> searchFats(@RequestParam String query) {
        return ResponseEntity.ok(productService.searchFat(query));
    }

    @GetMapping("/getFatNutrition")
    public ResponseEntity<Map<String, Object>> getFatNutrition(@RequestParam int foodId) {
        return ResponseEntity.ok(productService.getFatNutrition(foodId));
    }

    @PostMapping("/api/log")
    public ResponseEntity<Map<String, Object>> logFood(
            @RequestBody Map<String, Object> payload,
            Principal principal
    ) {
        logger.info("logFood payload: {}", payload);

        String username = principal.getName();
        User user = userRepository.getUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        int foodId = Integer.parseInt(payload.get("foodId").toString());
        String mealType = payload.get("mealType").toString();

        foodLogService.logFood(foodId, mealType, user.getId());

        return ResponseEntity.ok(Map.of("status", "success"));
    }

    @GetMapping("/logsRetrieve")
    public ResponseEntity<List<FoodLog>> getRetrieval(Authentication auth) {
        User user = userRepository.getUserByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("No User"));
        return ResponseEntity.ok(foodLogService.getFoodLogs(user.getId()));
    }
}

