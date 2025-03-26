package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmailController {
    
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribeEmail(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");

        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        // Print email address
        logger.info("New subscription request from email: {}", email);

        return ResponseEntity.ok().body("Subscription successful");
    }
}
