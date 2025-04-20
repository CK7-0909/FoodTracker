package com.example.foodtracker.service;

import com.example.foodtracker.API.UsdaAPI;
import com.example.foodtracker.Repository.FoodLogRepository;
import com.example.foodtracker.domain.FoodLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class FoodLogService {
    private final FoodLogRepository repo;
    private final UsdaAPI usdaApi;

    public FoodLogService(FoodLogRepository repo, UsdaAPI usdaApi) {
        this.repo = repo;
        this.usdaApi = usdaApi;
    }

    @Transactional
    public void logFood(Long userId, Long fdcId, String mealType) {
        Map<String, Object> details = usdaApi.getFoodDetails(String.valueOf(fdcId));

        BigDecimal cal = extract(details, "Energy");
        BigDecimal prot = extract(details, "Protein");
        BigDecimal fat = extract(details, "Total lipid (fat)");
        BigDecimal carbs = extract(details, "Carbohydrate, by difference");

        FoodLog entry = new FoodLog(userId, fdcId, mealType, cal, prot, fat, carbs);
        repo.logFood(entry);
    }

//    public List<FoodLog> getLogsForUser(Long userId) {
//        return repo.findByUserId(userId);
//    }

    @SuppressWarnings("unchecked")
    private BigDecimal extract(Map<String, Object> details, String key) {
        var list = (List<Map<String, Object>>) details.get("foodNutrients");
        for (var item : list) {
            var nut = (Map<String, Object>) item.get("nutrient");
            if (key.equals(nut.get("name"))) {
                Number amt = (Number) item.get("amount");
                return BigDecimal.valueOf(amt.doubleValue());
            }
        }
        return BigDecimal.ZERO;
    }
}
