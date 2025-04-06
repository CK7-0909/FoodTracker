package com.example.foodtracker.service;

import com.example.foodtracker.API.SpoonacularAPI;
import com.example.foodtracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.foodtracker.user.User;

import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SpoonacularAPI spoonacularAPI;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(SpoonacularAPI spoonacularAPI, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.spoonacularAPI = spoonacularAPI;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // For user registration
    public void registerUser(String email, String password) {
        if (userRepository.getUserByEmail(email) != null) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }
        if (!userRepository.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.addUser(user);
    }

    public User getUserByEmail(String email) {
        try {
            User userData = userRepository.getUserByEmail(email);
            User user = new User();
            user.setName(user.getName());
            user.setEmail(user.getEmail());
            user.setPassword(user.getPassword());
            user.setRole(userData.getRole());
            return user;

        } catch (EmptyResultDataAccessException e) {
            // Handle case when user doesn't exist
            return null; // Or throw a custom exception
        }
    }

    public Map<String, Object> searchRecipes(String query, int number) {
        return spoonacularAPI.searchRecipes(query, number);
    }

    public Map<String, Object> searchProducts(String query) {
        return spoonacularAPI.searchProduct(query);
    }

}
