package com.example.foodtracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping({"/about"})
    public String About() {
        return "about";
    }

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @GetMapping("/register")
    public String Register() {
        return "register";
    }

    @GetMapping("/index")
    public String Index() {
        return "index";
    }

    @GetMapping("/error")
    public String Error() {
        return "error";
    }

    @GetMapping("/dashboard")
    public String Dashboard() {
        return "dashboard";
    }

    @GetMapping("/logRetrieval")
    public String LogRetrieval() {
        return "logRetrieval";
    }
}
