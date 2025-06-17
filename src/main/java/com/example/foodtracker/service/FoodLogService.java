package com.example.foodtracker.service;

import com.example.foodtracker.API.FatSecretAPI;
import com.example.foodtracker.Repository.FoodLogRepository;
import com.example.foodtracker.domain.FoodLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        @SuppressWarnings("unchecked")
        Map<String, Object> resp = fatSecretApi.getFatNutrition(foodId);

        // 2) Drill into the nested Map structure
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

        // 4) Build your entity and map each column safely
        FoodLog log = new FoodLog();
        log.setUserId(userId);
        log.setFdcId(foodId);
        log.setMealType(mealTypeStr);
        log.setLoggedAt(LocalDateTime.now());

        // brand_name may be missing for generics — fall back to food_type
        String brandName = food.get("brand_name") != null
                ? food.get("brand_name").toString()
                : food.getOrDefault("food_type", "").toString();
        log.setBrandName(brandName);

        // metric_serving_amount → servingSize
        log.setServingSize(getAsBigDecimal(s, "metric_serving_amount"));

        // number_of_units → servings
        log.setServings(getAsBigDecimal(s, "number_of_units"));

        // nutrients — each key may be missing, so use helper
        log.setCalories(getAsBigDecimal(s, "calories"));
        log.setFat(getAsBigDecimal(s, "fat"));
        log.setSaturatedFat(getAsBigDecimal(s, "saturated_fat"));
        log.setTransFat(getAsBigDecimal(s, "trans_fat"));
        log.setCarbs(getAsBigDecimal(s, "carbohydrate"));
        log.setFiber(getAsBigDecimal(s, "fiber"));
        log.setProtein(getAsBigDecimal(s, "protein"));
        log.setCholesterol(getAsBigDecimal(s, "cholesterol"));
        log.setSodium(getAsBigDecimal(s, "sodium"));
        log.setSugar(getAsBigDecimal(s, "sugar"));
        log.setCalcium(getAsBigDecimal(s, "calcium"));
        log.setIron(getAsBigDecimal(s, "iron"));
        log.setPotassium(getAsBigDecimal(s, "potassium"));
        log.setPolyunsaturatedFat(getAsBigDecimal(s, "polyunsaturated_fat"));
        log.setMonounsaturatedFat(getAsBigDecimal(s, "monounsaturated_fat"));

        // 5) Persist it
        repo.logFood(log);
    }

    /**
     * Safely extract a BigDecimal from a map by key.
     * Returns ZERO if the key is missing, null, or cannot be parsed.
     */
    private BigDecimal getAsBigDecimal(Map<String, Object> map, String key) {
        Object raw = map.get(key);
        if (raw == null) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(raw.toString());
        } catch (NumberFormatException ex) {
            // could log a warning here if desired
            return BigDecimal.ZERO;
        }
    }

    @Transactional
    public List<FoodLog> getFoodLogs(int userId) {
        List<FoodLog> foodLogs = new ArrayList<>();
        foodLogs = repo.getFoodLogsByUser(userId);
        //System.out.println(foodLogs);
        return foodLogs;
    }

}
