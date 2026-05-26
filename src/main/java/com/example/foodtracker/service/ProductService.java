package com.example.foodtracker.service;

import com.example.foodtracker.API.FatSecretAPI;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {

    private final FatSecretAPI fatSecretAPI;

    public ProductService(FatSecretAPI fatSecretAPI) {
        this.fatSecretAPI = fatSecretAPI;
    }

    public Map<String, Object> searchFat(String query) {
        return fatSecretAPI.searchFoods(query);
    }

    public Map<String, Object> getFatNutrition(int id) {
        return fatSecretAPI.getFatNutrition(id);
    }
}
