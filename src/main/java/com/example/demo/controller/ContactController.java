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
public class ContactController {
    
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    
    @PostMapping("/contact")
    public ResponseEntity<?> submitContactForm(@RequestBody Map<String, String> formData) {
        // Print form data
        logger.info("Contact form submission received:");
        logger.info("Name: {}", formData.get("name"));
        logger.info("Email: {}", formData.get("email"));
        logger.info("Subject: {}", formData.get("subject"));
        logger.info("Message: {}", formData.get("message"));
        logger.info("Newsletter Subscription: {}", formData.get("newsletter"));
        return ResponseEntity.ok().body("Message sent successfully");
    }
}