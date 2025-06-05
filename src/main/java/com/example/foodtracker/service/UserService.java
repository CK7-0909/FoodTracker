package com.example.foodtracker.service;

import com.example.foodtracker.Repository.UserRepository;
import com.example.foodtracker.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // For user registration
    public void registerUser(String email, String password) {
        if (userRepository.getUserByEmail(email).isPresent()) {
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
            Optional<User> userDetail = userRepository.getUserByEmail(email);
            User userData = userDetail.orElseThrow(() -> new UsernameNotFoundException(email));
            User user = new User();
            user.setId(userData.getId());
            user.setName(userData.getName());
            user.setEmail(userData.getEmail());
            user.setPassword(userData.getPassword());
            user.setRole(userData.getRole());
            return user;

        } catch (EmptyResultDataAccessException e) {
            // Handle case when user doesn't exist
            return null; // Or throw a custom exception
        }
    }
}
