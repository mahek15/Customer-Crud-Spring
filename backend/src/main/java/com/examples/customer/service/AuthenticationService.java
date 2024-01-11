//package com.examples.customer.service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import javax.naming.AuthenticationException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class AuthenticationService {
//
//    @Value("${authentication.api.url}")
//    private String authenticationApiUrl;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public String authenticateAndGetBearerToken(String loginId, String password) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        Map<String, String> requestBody = new HashMap<>();
//        requestBody.put("login_id", loginId);
//        requestBody.put("password", password);
//
//        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(authenticationApiUrl, requestEntity, String.class);
//
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//            return responseEntity.getBody();
//        } else {
//            try {
//                throw new AuthenticationException("Authentication failed");
//            } catch (AuthenticationException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}
//
