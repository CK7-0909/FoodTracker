package com.example.foodtracker.service;

import com.example.foodtracker.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.foodtracker.user.User;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUserById(long id) {
        try {
            Map<String, Object> userData = userDAO.getUserByID(id);
            User user = new User();
            user.setId(id);
            user.setName((String) userData.get("name"));
            user.setEmail((String) userData.get("email"));
            user.setDob((String) userData.get("dob"));
            return user;

        } catch (EmptyResultDataAccessException e) {
            // Handle case when user doesn't exist
            return null; // Or throw a custom exception
        }
    }

}
