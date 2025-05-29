package com.example.foodtracker.Repository;

import com.example.foodtracker.domain.FoodLog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FoodLogRepository {
    private final JdbcTemplate jdbcTemplate;

    public FoodLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // add food
    public void logFood(FoodLog foodLog) {
        String sql = "insert into foodlog (user_id, fdc_id, meal_type, calories, fat, saturated_fat, trans_fat, carbs, fiber, protein, cholesterol, sodium, sugar, logged_at, brand_name, servings, serving_size) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, foodLog.getUserId(), foodLog.getFdcId(), foodLog.getMealType(), foodLog.getCalories(), foodLog.getFat(), foodLog.getSaturatedFat(), foodLog.getTransFat(),
                foodLog.getCarbs(), foodLog.getFiber(), foodLog.getProtein(), foodLog.getCholesterol(), foodLog.getSodium(), foodLog.getSugar(), foodLog.getLoggedAt(), foodLog.getBrandName(),
                foodLog.getServings(), foodLog.getServingSize());
    }
}
