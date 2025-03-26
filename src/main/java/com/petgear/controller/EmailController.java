package com.petgear.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class EmailController {
    
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
    
    @PostMapping("/subscribe")
    public ResponseEntity<Map<String, Object>> subscribeToNewsletter(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        
        // Log the received email address
        logger.info("Newsletter subscription request received for email: {}", email);
        
        // Here you would typically save the email to a database or send it to a mailing service
        // For now, we're just logging it as per the requirements
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Subscription successful");
        
        return ResponseEntity.ok(response);
    }
}