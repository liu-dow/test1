package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for static pages like About, Contact, Privacy Policy, and Terms of Service.
 */
@Controller
public class PageController {

    @GetMapping("/affiliate-disclosure")
    public String showAffiliateDisclosurePage() {
        return "affiliate-disclosure";
    }

    /**
     * Display the About Us page.
     *
     * @return The view name
     */
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    /**
     * Display the Contact page.
     *
     * @return The view name
     */
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    /**
     * Display the Privacy Policy page.
     *
     * @return The view name
     */
    @GetMapping("/privacy")
    public String privacyPage() {
        return "privacy";
    }

    /**
     * Display the Terms of Service page.
     *
     * @return The view name
     */
    @GetMapping("/terms")
    public String termsPage() {
        return "terms";
    }
} 