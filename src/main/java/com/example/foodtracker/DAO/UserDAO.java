package com.example.foodtracker.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getUserByID(long id) {
        return jdbcTemplate.queryForMap("select * from userinfo where id = ?", id);
    }
}
