package com.example.foodtracker.API;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class UsdaAPI {
    private final String apiKey;
    private final String baseUrl;
    private final RestTemplate restTemplate;

    public UsdaAPI(
            @Value("${usda.api.key}") String apiKey,
            @Value("${usda.api.base-url}") String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> searchFoods(String query) {
        String url = baseUrl + "/foods/search?api_key=" + apiKey + "&query=" + query;
        Map response = restTemplate.getForObject(url, Map.class);
        //System.out.println(response);
        return response;
    }

    public Map<String, Object> getFoodDetails(String fdcId) {
        String url = baseUrl + "/food/" + fdcId + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, Map.class);
    }
}
