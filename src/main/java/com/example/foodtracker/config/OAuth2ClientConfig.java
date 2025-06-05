/// / src/main/java/com/example/foodtracker/config/OAuth2ClientConfig.java
//package com.example.foodtracker.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.*;
//import org.springframework.security.oauth2.client.registration.*;
//import org.springframework.security.oauth2.client.web.*;
//import org.springframework.security.oauth2.client.web.reactive.function.client.*;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Configuration
//public class OAuth2ClientConfig {
//
//    @Bean
//    public OAuth2AuthorizedClientManager authorizedClientManager(
//            ClientRegistrationRepository clientRegs,
//            OAuth2AuthorizedClientService clientService) {
//
//        OAuth2AuthorizedClientProvider provider =
//                OAuth2AuthorizedClientProviderBuilder.builder()
//                        .clientCredentials()
//                        .build();
//
//        DefaultOAuth2AuthorizedClientManager manager =
//                new DefaultOAuth2AuthorizedClientManager(clientRegs, clientService);
//        manager.setAuthorizedClientProvider(provider);
//        return manager;
//    }
//
//    @Bean
//    public WebClient fatSecretWebClient(OAuth2AuthorizedClientManager manager) {
//        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
//                new ServletOAuth2AuthorizedClientExchangeFilterFunction(manager);
//        oauth2.setDefaultClientRegistrationId("fatsecret");
//
//        return WebClient.builder()
//                .baseUrl("https://platform.fatsecret.com/rest/server.api")
//                .apply(oauth2.oauth2Configuration())
//                .build();
//    }
//}
