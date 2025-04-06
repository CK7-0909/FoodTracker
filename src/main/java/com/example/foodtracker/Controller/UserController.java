package com.example.foodtracker.Controller;

import com.example.foodtracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.foodtracker.user.User;

import java.util.Map;

@RestController
// @RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getByUserEmail")
    public User getByUserId(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password) {
        try {
            userService.registerUser(email, password);
            return "redirect:/login";  // Redirect to login page after successful registration
        } catch (IllegalArgumentException e) {
            // If user already exists or email is invalid
            return "redirect:/register";  // You can redirect with an error message
        }
    }

    @GetMapping("/test")
    public String test() {
        return "API is working!";
    }

    @GetMapping("/searchRecipe")
    public ResponseEntity<Map<String, Object>> searchRecipes(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int number) {
        Map<String, Object> results = userService.searchRecipes(query, number);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/searchProduct")
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam String query) {
        Map<String, Object> results = userService.searchProducts(query);
        return ResponseEntity.ok(results);
    }
}
// Spring security password 60e53d06-1890-4728-86f4-b51f6ca657a6 and username is user and it's basic auth
