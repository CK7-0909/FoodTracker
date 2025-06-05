package com.example.foodtracker.service;

import com.example.foodtracker.API.FatSecretAPI;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {
    //private final UsdaAPI usdaAPI;
    //private final OpenFoodFactsAPI openFoodFactsAPI;
    private final FatSecretAPI fatSecretAPI;

    public ProductService(FatSecretAPI fatSecretAPI) {
        //this.usdaAPI = usdaAPI;
        //this.openFoodFactsAPI = openFoodFactsAPI;
        this.fatSecretAPI = fatSecretAPI;
    }

    public Map<String, Object> searchFat(String query) {
        Map<String, Object> response = fatSecretAPI.searchFoods(query);
        //System.out.println(response);
        return response;
    }

    public Map<String, Object> getFatNutrition(int id) {
        Map<String, Object> response = fatSecretAPI.getFatNutrition(id);
        //System.out.println(response);
        return response;
    }

//    public Map<String, Object> searchProducts(String query) {
//        Map<String, Object> apiResponse = usdaAPI.searchFoods(query);
//        List<Map<String, Object>> foods = (List<Map<String, Object>>) apiResponse.get("foods");
//
//        List<Map<String, Object>> simplified = new ArrayList<>(); // Creating a list
//
//        for (Map<String, Object> food : foods) {
//            Map<String, Object> product = new HashMap<>();
//            product.put("id", food.get("fdcId")); // needed for detail lookup
//            product.put("title", food.get("description")); // shown in frontend
//
//            // 1) grab the raw UPC field (may be List, String, whatever)
//            Object upcRaw = food.get("gtinUpcs");  // note: USDA uses "gtinUpcs"
//            // 2) convert to String (will yield "[12345]" or "12345" if single)
//            String upcStr = upcRaw != null ? upcRaw.toString().replaceAll("[\\[\\]]", "") : null;
//            String img = null;
//            if (upcStr != null && !upcStr.isEmpty()) {
//                img = openFoodFactsAPI.getImageUrlByUpc(upcStr);
//            }
//            //System.out.println("FDCid=" + food.get("fdcId") + " UPCs=" + food.get("gtinUpc"));
//            product.put("brand", food.get("brandOwner"));
//            product.put("image", img != null ? img: "/img/placeholder.png"); // required for UI
//            simplified.add(product);
//        }
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("products", simplified);
//        //System.out.println(result); // Add this line to log the result
//
//        return result;
//    }
//
//
//    public Map<String, Object> getProductDetails(String fdcId) {
//        return usdaAPI.getFoodDetails(fdcId);
//    }
}
