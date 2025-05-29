package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BrandsController {
    @GetMapping("/brands")
    public String showBrandsPage() {
        return "brands/brands";
    }
    @GetMapping("/brands/{brand}")
    public String showBrandsPage(@PathVariable String brand) {
        // Map all available blog posts to their templates
        return "brands/" + brand;

    }
}
