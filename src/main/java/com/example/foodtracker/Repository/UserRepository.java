package com.example.foodtracker.Repository;

import com.example.foodtracker.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.regex.Pattern;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // for login validation
    public Optional<User> getUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM userinfo WHERE email = ?";
            User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();  // Return null if no user found
        }
    }

    // add user
    public void addUser(User user) {
        String sql = "insert into userinfo (email, password) values (?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
    }

    // Method to check if the email format is valid
    public boolean isValidEmail(String email) {
        // Simple regex for basic email format validation (can be enhanced)
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
}
