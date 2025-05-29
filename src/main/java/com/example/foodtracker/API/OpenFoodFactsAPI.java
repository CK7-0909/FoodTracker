//package com.example.foodtracker.API;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
//@Component
//public class OpenFoodFactsAPI {
//    private final RestTemplate rest = new RestTemplate();
//
//    /**
//     * Given a 12- or 13-digit UPC code, returns the front-of-package image URL
//     * or null if none found.
//     */
//    public String getImageUrlByUpc(String upc) {
//        try {
//            String url = "https://world.openfoodfacts.org/api/v0/product/"
//                    + upc + ".json";
//            Map<String, Object> resp = rest.getForObject(url, Map.class);
//            Map<String, Object> product = (Map<String, Object>) resp.get("product");
//            if (product == null) return null;
//            return (String) product.get("image_front_small_url");
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
