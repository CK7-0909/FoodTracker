//package com.example.foodtracker.Controller;
//
//import com.example.foodtracker.dto.FoodLogRequestDto;
//import com.example.foodtracker.service.FoodLogService;
//import com.example.foodtracker.service.UserService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/log")
//public class FoodLogController {
//    private final FoodLogService foodLogService;
//    //private final UserRepository userRepository;
//    private final UserService userService;
//
//    public FoodLogController(FoodLogService foodLogService, UserService userService) {
//        this.foodLogService = foodLogService;
//        //this.userRepository = userRepository;
//        this.userService = userService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Map<String, String>> logFood(
//            @RequestBody FoodLogRequestDto req,
//            @AuthenticationPrincipal UserDetails user
//    ) {
//        String email = user.getUsername();
//        Long userId = userService.getUserByEmail(email).getId();
//        //System.out.println(userId);
//        foodLogService.logFood(userId, req.getFdcId(), req.getMealType());
//        return ResponseEntity.ok(Map.of("status", "success"));
//    }
//}
