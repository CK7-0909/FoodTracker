package com.example.foodtracker.API;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

@Component
public class FatSecretAPI {

    private final String clientId;
    private final String clientSecret;
    private final String tokenUrl;
    private final String apiUrl;
    private final RestTemplate restTemplate;

    private String accessToken;  // Cache token for reuse (optional simple caching)

    public FatSecretAPI(
            @Value("${fatsecret.api.client-id}") String clientId,
            @Value("${fatsecret.api.client-secret}") String clientSecret,
            @Value("${fatsecret.api.token-url}") String tokenUrl,
            @Value("${fatsecret.api.api-url}") String apiUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenUrl = tokenUrl;
        this.apiUrl = apiUrl;
        this.restTemplate = new RestTemplate();
    }

    // 1. Get Access Token
    public String getAccessToken() {
        if (accessToken != null) {
            return accessToken;  // Simple reuse (no expiration check yet)
        }

        String authStr = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(authStr.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + encodedAuth);

        String body = "grant_type=client_credentials&scope=basic";
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            accessToken = (String) response.getBody().get("access_token");
            return accessToken;
        } else {
            throw new RuntimeException("Failed to get access token: " + response.getStatusCode());
        }
    }

    // 2. Search foods
    public Map<String, Object> searchFoods(String query) {
        String token = getAccessToken();  // Get token (cached if available)

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        String url = apiUrl + "?method=foods.search&search_expression=" + query + "&format=json";

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Map.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to search foods: " + response.getStatusCode());
        }
    }

    // 3. Get Nutrition
    public Map<String, Object> getFatNutrition(int id) {
        String token = getAccessToken();  // Get token (cached if available)

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        String url = apiUrl + "?method=food.get.v2" + "&food_id=" + id + "&format=json";

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Map.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to search foods: " + response.getStatusCode());
        }
    }
}
