package com.example.foodtracker.API;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class SpoonacularAPI {
    private final String apiKey;
    private final String baseUrl = "https://api.spoonacular.com";
    private final RestTemplate restTemplate;

    public SpoonacularAPI(@Value("${spoonacular.api.key}") String apiKey) {
        this.apiKey = apiKey;
        restTemplate = new RestTemplate();
    }

    public Map<String, Object> searchRecipes(String query, int number) {
        String endpoint = "/recipes/complexSearch";
        String url = baseUrl + endpoint + "?apiKey=" + apiKey + "&query=" + query + "&number=" + number;
        return restTemplate.getForObject(url, Map.class);
    }

//    public Map<String, Object> searchProduct(String query) {
//        String endpoint = "/food/products/search";
//        String url = baseUrl + endpoint + "?apiKey=" + apiKey + "&query=" + query;
//        return restTemplate.getForObject(url, Map.class);
//    }

}
