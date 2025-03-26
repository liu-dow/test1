package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {

    /**
     * Display blog list page
     */
    @GetMapping
    public String showBlogListPage() {
        return "blog/blogs";
    }

    /**
     * Display single blog post page
     */
    @GetMapping("/{slug}")
    public String showBlogPostPage(@PathVariable String slug) {
        // Return the corresponding blog page based on slug
        if ("pawfit-3-gps-tracker-review".equals(slug)) {
            return "blog/pawfit-3-gps-tracker-review";
        } else if ("pitpat-gps-tracker-review".equals(slug)) {
            return "blog/pitpat-gps-tracker-review";
        } else if (slug.equals("pawfit-lite-gps-tracker-review")) {
            return "blog/pawfit-lite-gps-tracker-review";
        }

        // For other blog articles, more conditions can be added
        // Or implement a generic blog article page template

        return "blog/blogs"; // If the corresponding blog is not found, redirect to the blog list page
    }

    /**
     * Process blog search requests
     */
    @GetMapping("/search")
    public String searchBlogs() {
        // In actual application, this will process search parameters and return search results
        return "blog/blogs";
    }
}