package com.example.foodtracker.Controller;

import com.example.foodtracker.Model.User;
import com.example.foodtracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getByUserEmail")
    @ResponseBody
    public User getByUserEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password) {
        try {
            userService.registerUser(email, password);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            return "redirect:/register";
        }
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "API is working!";
    }
}
