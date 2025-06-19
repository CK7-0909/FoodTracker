package com.example.foodtracker.Repository;

import com.example.foodtracker.domain.FoodLog;
import com.example.foodtracker.dto.MacroSummaryDto;
import com.example.foodtracker.dto.MicroSummaryDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FoodLogRepository {
    private final JdbcTemplate jdbcTemplate;

    public FoodLogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // add food
    public void logFood(FoodLog foodLog) {
        String sql = "insert into foodlog (user_id, fdc_id, meal_type, calories, fat, saturated_fat, trans_fat, carbs, fiber, protein, cholesterol, sodium, sugar, logged_at, brand_name, " +
                "servings, serving_size, calcium, iron, potassium, polyunsaturated_fat, monounsaturated_fat, vitamin_a, vitamin_c, vitamin_d) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, foodLog.getUserId(), foodLog.getFdcId(), foodLog.getMealType(), foodLog.getCalories(), foodLog.getFat(), foodLog.getSaturatedFat(), foodLog.getTransFat(),
                foodLog.getCarbs(), foodLog.getFiber(), foodLog.getProtein(), foodLog.getCholesterol(), foodLog.getSodium(), foodLog.getSugar(), foodLog.getLoggedAt(), foodLog.getBrandName(),
                foodLog.getServings(), foodLog.getServingSize(), foodLog.getCalcium(), foodLog.getIron(), foodLog.getPotassium(), foodLog.getPolyunsaturatedFat(), foodLog.getMonounsaturatedFat(),
                foodLog.getVitaminA(), foodLog.getVitaminC(), foodLog.getVitaminD());
    }

    // retrive all food logs
    public List<FoodLog> getFoodLogsByUser(int userId) {
        String sql = """
                    SELECT id,
                      user_id,
                      fdc_id,
                      meal_type,
                      logged_at,
                      brand_name,
                      serving_size,
                      servings,
                      calories,
                      fat,
                      saturated_fat,
                      trans_fat,
                      carbs,
                      fiber,
                      protein,
                      cholesterol,
                      sodium,
                      sugar,
                      calcium,
                      iron,
                      potassium,
                      polyunsaturated_fat,
                      monounsaturated_fat,
                      vitamin_a,
                      vitamin_c,
                      vitamin_d
                    FROM foodlog
                    WHERE user_id = ?
                    ORDER BY logged_at DESC
                """;
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(FoodLog.class),
                userId
        );
    }

    public MacroSummaryDto getMacroSummary(long userId, LocalDateTime start, LocalDateTime end) {
        String sql = """
                SELECT
                  COALESCE(SUM(protein), 0) AS protein,
                  COALESCE(SUM(carbs), 0) AS carbs,
                  COALESCE(SUM(fat), 0) AS fat,
                  COALESCE(SUM(fiber), 0) AS fiber,
                  COALESCE(SUM(cholesterol), 0) AS cholesterol,
                  COALESCE(SUM(sodium), 0) AS sodium,
                  COALESCE(SUM(sugar), 0) AS sugar,
                  COALESCE(SUM(calcium), 0) AS calcium,
                  COALESCE(SUM(potassium), 0) AS potassium,
                  COALESCE(SUM(vitamin_a), 0) AS vitaminA,
                  COALESCE(SUM(vitamin_c), 0) AS vitaminC,
                  COALESCE(SUM(vitamin_d), 0) AS vitaminD
                FROM foodlog
                WHERE user_id = ? AND logged_at >= ? AND logged_at < ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(MacroSummaryDto.class),
                userId,
                start,
                end
        );
    }

    public MicroSummaryDto getMicroSummary(long userId, LocalDateTime start, LocalDateTime end) {
        String sql = """
                SELECT
                  COALESCE(SUM(fiber), 0) AS fiber,
                  COALESCE(SUM(cholesterol), 0) AS cholesterol,
                  COALESCE(SUM(sodium), 0) AS sodium,
                  COALESCE(SUM(sugar), 0) AS sugar,
                  COALESCE(SUM(calcium), 0) AS calcium,
                  COALESCE(SUM(potassium), 0) AS potassium,
                  COALESCE(SUM(vitamin_a), 0) AS vitaminA,
                  COALESCE(SUM(vitamin_c), 0) AS vitaminC,
                  COALESCE(SUM(vitamin_d), 0) AS vitaminD
                
                FROM foodlog
                WHERE user_id = ? AND logged_at >= ? AND logged_at < ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(MicroSummaryDto.class),
                userId,
                start,
                end
        );
    }

}
