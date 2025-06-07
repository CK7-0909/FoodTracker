package com.example.foodtracker.Controller;

import com.example.foodtracker.Repository.UserRepository;
import com.example.foodtracker.domain.User;
import com.example.foodtracker.dto.MacroSummaryDto;
import com.example.foodtracker.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class DashController {

    private final UserRepository userRepository;
    private final DashboardService dashboardService;

    public DashController(UserRepository userRepository, DashboardService dashboardService) {
        this.userRepository = userRepository;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/macroSummaryChart")
    public ResponseEntity<MacroSummaryDto> macroSummary(@RequestParam(required = false) String start,
                                                        @RequestParam(required = false) String end,
                                                        Authentication auth) {
        User user = userRepository.getUserByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("No User"));
        LocalDate startDate = start != null ? LocalDate.parse(start) : LocalDate.now();
        LocalDate endDate = end != null ? LocalDate.parse(end) : LocalDate.now();
        MacroSummaryDto summary = dashboardService.getMacroSummary(user.getId(), startDate, endDate);
        return ResponseEntity.ok(summary);
    }

//    @GetMapping("/macroSummaryChartTable")
//    public ResponseEntity<List<MacroHistoryDto>> macroHistory(@RequestParam(required = false) String start,
//                                                              @RequestParam(required = false) String end,
//                                                              Authentication auth) throws JsonProcessingException {
//        User user = userRepository.getUserByEmail(auth.getName())
//                .orElseThrow(() -> new UsernameNotFoundException("No User"));
//        LocalDate startDate = start != null ? LocalDate.parse(start): LocalDate.now();
//        LocalDate endDate = end != null ? LocalDate.parse(end): LocalDate.now();
//        List<MacroHistoryDto> history = dashboardService.getMacroSummary(user.getId(), startDate, endDate);
//        return ResponseEntity.ok(history);
//    }
}
