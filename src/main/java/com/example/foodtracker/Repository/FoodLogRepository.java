package com.example.foodtracker.Repository;

import com.example.foodtracker.domain.FoodLog;
import com.example.foodtracker.dto.MacroHistoryDto;
import com.example.foodtracker.dto.MacroSummaryDto;
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
        String sql = "insert into foodlog (user_id, fdc_id, meal_type, calories, fat, saturated_fat, trans_fat, carbs, fiber, protein, cholesterol, sodium, sugar, logged_at, brand_name, servings, serving_size) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, foodLog.getUserId(), foodLog.getFdcId(), foodLog.getMealType(), foodLog.getCalories(), foodLog.getFat(), foodLog.getSaturatedFat(), foodLog.getTransFat(),
                foodLog.getCarbs(), foodLog.getFiber(), foodLog.getProtein(), foodLog.getCholesterol(), foodLog.getSodium(), foodLog.getSugar(), foodLog.getLoggedAt(), foodLog.getBrandName(),
                foodLog.getServings(), foodLog.getServingSize());
    }

    // retrive all food logs
    public List<FoodLog> getFoodLogsByUser(int userId) {
        String sql = """
                    SELECT 
                      id,
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
                      sugar
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
                  COALESCE(SUM(fat), 0) AS fat
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

    public List<MacroHistoryDto> getMacroHistory(long userId, LocalDateTime start, LocalDateTime end) {
        String sql = """
                SELECT DATE(logged_at) AS day,
                       COALESCE(SUM(protein), 0) AS protein,
                       COALESCE(SUM(carbs), 0) AS carbs,
                       COALESCE(SUM(fat), 0) AS fat
                FROM foodlog
                WHERE user_id = ? AND logged_at >= ? AND logged_at < ?
                GROUP BY DATE(logged_at)
                ORDER BY DATE(logged_at)
                """;

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(MacroHistoryDto.class),
                userId,
                start,
                end
        );
    }
}
