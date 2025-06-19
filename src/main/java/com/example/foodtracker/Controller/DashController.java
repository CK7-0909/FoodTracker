package com.example.foodtracker.Controller;

import com.example.foodtracker.Repository.UserRepository;
import com.example.foodtracker.domain.User;
import com.example.foodtracker.dto.MacroSummaryDto;
import com.example.foodtracker.service.DashboardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<MacroSummaryDto> macroSummary(@RequestParam(required = false)
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate start,

                                                        @RequestParam(required = false)
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate end,
                                                        Authentication auth) {
        User user = userRepository.getUserByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("No User"));
        LocalDate actualEnd = end != null
                ? end
                : LocalDate.now();

        LocalDate actualStart = start != null
                ? start
                : actualEnd.minusMonths(1);

        if (actualStart.isAfter(actualEnd)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "start date must be on or before end date"
            );
        }
        MacroSummaryDto summary = dashboardService.getMacroSummary(user.getId(), actualStart, actualEnd);
        return ResponseEntity.ok(summary);
    }

//    @GetMapping("/macroSummaryChart")
//    public ResponseEntity<MicroSummaryDto> microSummary(@RequestParam(required = false)
//                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//                                                        LocalDate start,
//
//                                                        @RequestParam(required = false)
//                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//                                                        LocalDate end,
//                                                        Authentication auth) {
//        User user = userRepository.getUserByEmail(auth.getName())
//                .orElseThrow(() -> new UsernameNotFoundException("No User"));
//        LocalDate actualEnd = end != null
//                ? end
//                : LocalDate.now();
//
//        LocalDate actualStart = start != null
//                ? start
//                : actualEnd.minusMonths(1);
//
//        if (actualStart.isAfter(actualEnd)) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "start date must be on or before end date"
//            );
//        }
//        MicroSummaryDto summary = dashboardService.getMicroSummary(user.getId(), actualStart, actualEnd);
//        return ResponseEntity.ok(summary);
//    }
}
