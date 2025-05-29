package com.example.foodtracker.service;

//import com.example.foodtracker.API.UsdaAPI;

import com.example.foodtracker.API.FatSecretAPI;
import com.example.foodtracker.Repository.FoodLogRepository;
import com.example.foodtracker.domain.FoodLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class FoodLogService {
    private final FoodLogRepository repo;
    private final FatSecretAPI fatSecretApi;

    public FoodLogService(FoodLogRepository repo,
                          FatSecretAPI fatSecretApi) {
        this.repo = repo;
        this.fatSecretApi = fatSecretApi;
    }

    @Transactional
    public void logFood(int foodId, String mealTypeStr, Integer userId) {
        // 1) Call FatSecret to get the raw JSON map
        Map<String, Object> resp = fatSecretApi.getFatNutrition(foodId);

        // 2) Drill into the nested Map structure:
        @SuppressWarnings("unchecked")
        Map<String, Object> food = (Map<String, Object>) resp.get("food");
        @SuppressWarnings("unchecked")
        Object servingsObj = ((Map<String, Object>) food.get("servings")).get("serving");

        // 3) Normalize to a single serving Map
        @SuppressWarnings("unchecked")
        Map<String, Object> s;
        if (servingsObj instanceof List) {
            s = ((List<Map<String, Object>>) servingsObj).get(0);
        } else {
            s = (Map<String, Object>) servingsObj;
        }

        // 4) Build your entity and map each column
        FoodLog log = new FoodLog();
        log.setUserId(userId);
        log.setFdcId(foodId);
        log.setMealType(mealTypeStr);
        log.setLoggedAt(LocalDateTime.now());
        log.setBrandName((String) food.get("brand_name"));
        log.setServingSize(new BigDecimal(s.get("metric_serving_amount").toString()));
        log.setServings(new BigDecimal(s.get("number_of_units").toString()));
        log.setCalories(new BigDecimal(s.get("calories").toString()));
        log.setFat(new BigDecimal(s.get("fat").toString()));
        log.setSaturatedFat(new BigDecimal(s.get("saturated_fat").toString()));
        log.setTransFat(new BigDecimal(s.get("trans_fat").toString()));
        log.setCarbs(new BigDecimal(s.get("carbohydrate").toString()));
        log.setFiber(new BigDecimal(s.get("fiber").toString()));
        log.setProtein(new BigDecimal(s.get("protein").toString()));
        log.setCholesterol(new BigDecimal(s.get("cholesterol").toString()));
        log.setSodium(new BigDecimal(s.get("sodium").toString()));
        log.setSugar(new BigDecimal(s.get("sugar").toString()));

        // 5) Persist it
        repo.logFood(log);
    }
}

