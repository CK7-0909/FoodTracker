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
        String sql = "insert into userFoodlog (user_Id, fdc_Id, meal_Type, calories, protein, fat, carbs) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, foodLog.getUserId(), foodLog.getFdcId(), foodLog.getMealType(), foodLog.getCalories(), foodLog.getProtein(),
                foodLog.getFat(), foodLog.getCarbs());
    }
}
