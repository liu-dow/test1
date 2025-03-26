package com.petgear.controller;

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
        // Blog content is hardcoded directly in the blogs.html template
        return "blog/blogs";
    }

    /**
     * Display single blog post page
     */
    @GetMapping("/{slug}")
    public String showBlogPostPage(@PathVariable String slug) {
        // Map all available blog posts to their templates
        switch (slug) {
            case "pawfit-3-gps-tracker-review":
            case "pawfit-lite-gps-tracker-review":
            case "pitpat-gps-tracker-review":
            case "best-gps-pet-trackers-2024":
            case "smart-pet-cameras-guide":
                return "blog/" + slug;
            default:
                return "blog/blogs"; // Redirect to blog list if post not found
        }

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