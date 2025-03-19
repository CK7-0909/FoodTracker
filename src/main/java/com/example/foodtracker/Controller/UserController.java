package com.example.foodtracker.Controller;

import com.example.foodtracker.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.foodtracker.user.User;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getByUserId")
    public User getByUserId(@RequestParam long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/test")
    public String test() {
        return "API is working!";
    }
}
// Spring security password 60e53d06-1890-4728-86f4-b51f6ca657a6 and username is user and it's basic auth
