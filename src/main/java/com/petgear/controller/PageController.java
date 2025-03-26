package com.petgear.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/affiliate-disclosure")
    public String showAffiliateDisclosurePage() {
        return "affiliate-disclosure";
    }
    
    // ... existing code ...
} 