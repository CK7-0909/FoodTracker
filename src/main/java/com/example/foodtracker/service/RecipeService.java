package com.example.foodtracker.service;

import com.example.foodtracker.API.SpoonacularAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeService {
    private final SpoonacularAPI spoonacularAPI;

    @Autowired
    public RecipeService(SpoonacularAPI spoonacularAPI) {
        this.spoonacularAPI = spoonacularAPI;
    }

    public Map<String, Object> searchRecipes(String query, int number) {
        return spoonacularAPI.searchRecipes(query, number);
    }


}
