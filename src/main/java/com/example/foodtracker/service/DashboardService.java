package com.example.foodtracker.service;

import com.example.foodtracker.Repository.FoodLogRepository;
import com.example.foodtracker.dto.MacroSummaryDto;
import com.example.foodtracker.dto.MicroSummaryDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DashboardService {

    private final FoodLogRepository foodLogRepository;

    public DashboardService(FoodLogRepository foodLogRepository) {
        this.foodLogRepository = foodLogRepository;
    }

    @Transactional(readOnly = true)
    public MacroSummaryDto getMacroSummary(int userId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.plusDays(1).atStartOfDay();
        return foodLogRepository.getMacroSummary(userId, start, end);
    }

    @Transactional(readOnly = true)
    public MicroSummaryDto getMicroSummary(int userId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.plusDays(1).atStartOfDay();
        return foodLogRepository.getMicroSummary(userId, start, end);
    }

}
