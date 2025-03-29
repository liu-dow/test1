package com.petgear.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

/**
 * Controller for handling pet tracker related requests
 */
@Controller
@RequestMapping("/api/trackers")
public class TrackerController {

    /**
     * Get Pawfit 3 tracker details
     * @return Tracker details as JSON
     */
    @GetMapping("/pawfit")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getPawfitDetails() {
        Map<String, Object> tracker = new HashMap<>();
        tracker.put("id", "pawfit-3");
        tracker.put("name", "Pawfit 3 GPS Pet Tracker");
        tracker.put("price", 49.99);
        tracker.put("currency", "USD");
        tracker.put("rating", 4.5);
        tracker.put("reviewCount", 256);
        tracker.put("description", "Advanced GPS pet tracker with real-time location tracking, temperature monitoring, and 7-day battery life");
        
        Map<String, Object> features = new HashMap<>();
        features.put("batteryLife", "7 days");
        features.put("waterproof", "IP67");
        features.put("gpsAccuracy", "3-5 meters");
        features.put("weight", "30g");
        features.put("temperatureMonitoring", true);
        features.put("activityTracking", true);
        features.put("ledLight", true);
        
        Map<String, Object> subscription = new HashMap<>();
        subscription.put("monthly", 3.99);
        subscription.put("annual", 39.99);
        subscription.put("twoYear", 71.99);
        
        tracker.put("features", features);
        tracker.put("subscription", subscription);
        tracker.put("imageUrl", "/images/pawfit-3-large.jpg");
        
        return ResponseEntity.ok(tracker);
    }

    /**
     * Get Tractive GPS tracker details
     * @return Tracker details as JSON
     */
    @GetMapping("/tractive")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getTractiveDetails() {
        Map<String, Object> tracker = new HashMap<>();
        tracker.put("id", "tractive");
        tracker.put("name", "Tractive GPS Pet Tracker");
        tracker.put("price", 44.99);
        tracker.put("currency", "USD");
        tracker.put("rating", 4.0);
        tracker.put("reviewCount", 312);
        tracker.put("description", "GPS pet tracker with worldwide coverage, real-time location tracking, and activity monitoring");
        
        Map<String, Object> features = new HashMap<>();
        features.put("batteryLife", "2-5 days");
        features.put("waterproof", "IPX7");
        features.put("gpsAccuracy", "5-10 meters");
        features.put("weight", "35g");
        features.put("globalCoverage", true);
        features.put("activityTracking", true);
        features.put("virtualFence", true);
        
        Map<String, Object> subscription = new HashMap<>();
        subscription.put("monthly", 3.33);
        subscription.put("annual", 33.30);
        subscription.put("twoYear", 59.99);
        
        tracker.put("features", features);
        tracker.put("subscription", subscription);
        tracker.put("imageUrl", "/images/tractive-large.jpg");
        
        return ResponseEntity.ok(tracker);
    }

    /**
     * Get PitPat activity monitor details
     * @return Tracker details as JSON
     */
    @GetMapping("/pitpat")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getPitPatDetails() {
        Map<String, Object> tracker = new HashMap<>();
        tracker.put("id", "pitpat");
        tracker.put("name", "PitPat Dog Activity Monitor");
        tracker.put("price", 39.99);
        tracker.put("currency", "USD");
        tracker.put("rating", 4.8);
        tracker.put("reviewCount", 428);
        tracker.put("description", "Lightweight dog activity monitor with 1-year battery life and breed-specific exercise goals");
        
        Map<String, Object> features = new HashMap<>();
        features.put("batteryLife", "365 days");
        features.put("waterproof", "Yes");
        features.put("gpsTracking", false);
        features.put("weight", "8g");
        features.put("breedSpecificGoals", true);
        features.put("activityTracking", true);
        features.put("requiresSubscription", false);
        
        tracker.put("features", features);
        tracker.put("subscription", null);
        tracker.put("imageUrl", "/images/pitpat-large.jpg");
        
        return ResponseEntity.ok(tracker);
    }

    /**
     * Get comparison data for all trackers
     * @return Comparison data as JSON
     */
    @GetMapping("/comparison")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> getComparisonData() {
        // Call individual methods to get tracker data
        Map<String, Object> pawfit = getPawfitDetails().getBody();
        Map<String, Object> tractive = getTractiveDetails().getBody();
        Map<String, Object> pitpat = getPitPatDetails().getBody();
        
        // Add comparison-specific attributes
        pawfit.put("bestFor", "Medium to large dogs requiring reliable tracking");
        tractive.put("bestFor", "International travelers with pets");
        pitpat.put("bestFor", "Health-conscious dog owners on a budget");
        
        return ResponseEntity.ok(Arrays.asList(pawfit, tractive, pitpat));
    }

    /**
     * Render the Pawfit 3 detail page
     */
    @GetMapping("/view/pawfit-3")
    public String viewPawfit(Model model) {
        model.addAttribute("tracker", getPawfitDetails().getBody());
        return "accessories/trackers/pawfit-3";
    }

    /**
     * Render the Tractive detail page
     */
    @GetMapping("/view/tractive")
    public String viewTractive(Model model) {
        model.addAttribute("tracker", getTractiveDetails().getBody());
        return "accessories/trackers/tractive";
    }

    /**
     * Render the PitPat detail page
     */
    @GetMapping("/view/pitpat")
    public String viewPitPat(Model model) {
        model.addAttribute("tracker", getPitPatDetails().getBody());
        return "accessories/trackers/pitpat";
    }

    /**
     * Render the comparison page
     */
    @GetMapping("/view/comparison")
    public String viewComparison(Model model) {
        model.addAttribute("trackers", getComparisonData().getBody());
        return "accessories/trackers/comparison";
    }
}