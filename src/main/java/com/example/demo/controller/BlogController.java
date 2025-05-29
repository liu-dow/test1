package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/blogs")
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

}