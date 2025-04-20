package com.example.foodtracker.service;

import com.example.foodtracker.API.UsdaAPI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final UsdaAPI usdaAPI;

    public ProductService(UsdaAPI usdaAPI) {
        this.usdaAPI = usdaAPI;
    }

    public Map<String, Object> searchProducts(String query) {
        Map<String, Object> apiResponse = usdaAPI.searchFoods(query);
        List<Map<String, Object>> foods = (List<Map<String, Object>>) apiResponse.get("foods");

        List<Map<String, Object>> simplified = new ArrayList<>(); // Creating a list

        for (Map<String, Object> food : foods) {
            Map<String, Object> product = new HashMap<>();
            product.put("id", food.get("fdcId")); // needed for detail lookup
            product.put("title", food.get("description")); // shown in frontend
            product.put("image", "/img/placeholder.png"); // required for UI
            simplified.add(product);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("products", simplified);
        //System.out.println(result); // Add this line to log the result

        return result;
    }


    public Map<String, Object> getProductDetails(String fdcId) {
        return usdaAPI.getFoodDetails(fdcId);
    }
}
