package com.example.foodtracker.service;

import com.example.foodtracker.Repository.FoodLogRepository;
import com.example.foodtracker.dto.MacroSummaryDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DashboardService {

    private final FoodLogRepository foodLogRepository;

    public DashboardService(FoodLogRepository foodLogRepository) {
        this.foodLogRepository = foodLogRepository;
    }

    @Transactional(readOnly = true)
    public MacroSummaryDto getMacroSummary(int userId, String period) {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start;
        switch (period == null ? "day" : period.toLowerCase()) {
            case "week" -> start = end.minusWeeks(1);
            case "month" -> start = end.minusMonths(1);
            default -> start = end.minusDays(1);
        }
        return foodLogRepository.getMacroSummary(userId, start, end);
    }
}
