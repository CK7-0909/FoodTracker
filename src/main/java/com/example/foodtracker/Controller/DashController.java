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

@RestController
public class DashController {

    private final UserRepository userRepository;
    private final DashboardService dashboardService;

    public DashController(UserRepository userRepository, DashboardService dashboardService) {
        this.userRepository = userRepository;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/macroSummary")
    public ResponseEntity<MacroSummaryDto> macroSummary(@RequestParam(defaultValue = "day") String period,
                                                        Authentication auth) {
        User user = userRepository.getUserByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("No User"));
        MacroSummaryDto summary = dashboardService.getMacroSummary(user.getId(), period);
        return ResponseEntity.ok(summary);
    }
}
