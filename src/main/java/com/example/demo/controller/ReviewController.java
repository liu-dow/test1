package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @GetMapping("/{slug}")
    public String showReviewPostPage(@PathVariable String slug) {
        // Return the corresponding blog page based on slug
        if ("pawfit-3-gps-tracker-review".equals(slug)) {
            return "reviews/pawfit-3-gps-tracker-review";
        } else if ("pitpat-gps-tracker-review".equals(slug)) {
            return "reviews/pitpat-gps-tracker-review";
        } else if (slug.equals("pawfit-lite-gps-tracker-review")) {
            return "reviews/pawfit-lite-gps-tracker-review";
        } else if ("tractive-gps-review".equals(slug)) {
            return "reviews/tractive-gps-review";
        }
        // For other articles, more conditions can be added
        // Or implement a generic blog article page template
        return "index";
    }


}
