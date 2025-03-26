package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * REST controller for tracker-related API endpoints.
 */
@RestController
@RequestMapping("/api/trackers")
public class TrackerApiController {

    /**
     * Get all trackers.
     *
     * @return List of all trackers
     */
    @GetMapping
    public List<Map<String, Object>> getAllTrackers() {
        List<Map<String, Object>> trackerList = new ArrayList<>();
        
        // Pawfit 3
        Map<String, Object> pawfit = new HashMap<>();
        pawfit.put("id", "pawfit-3");
        pawfit.put("name", "Pawfit 3 GPS Pet Tracker");
        pawfit.put("category", "GPS & Activity Tracker");
        pawfit.put("price", 49.99);
        pawfit.put("currency", "USD");
        pawfit.put("subscriptionInfo", "+ Subscription");
        pawfit.put("imageUrl", "/images/pawfit-3.jpg");
        pawfit.put("badge", "Best Overall");
        pawfit.put("features", Arrays.asList("7-Day Battery", "Real-time GPS", "Temperature Alerts", "IP67 Waterproof", "LED Light"));
        pawfit.put("rating", 4.5);
        pawfit.put("reviewCount", 256);
        pawfit.put("description", "Advanced GPS pet tracker with real-time location tracking, temperature monitoring, and 7-day battery life");
        pawfit.put("bestFor", "Medium to large dogs requiring reliable tracking");
        
        // Tractive GPS
        Map<String, Object> tractive = new HashMap<>();
        tractive.put("id", "tractive");
        tractive.put("name", "Tractive GPS Pet Tracker");
        tractive.put("category", "GPS Tracker");
        tractive.put("price", 44.99);
        tractive.put("currency", "USD");
        tractive.put("subscriptionInfo", "+ Subscription");
        tractive.put("imageUrl", "/images/tractive.jpg");
        tractive.put("badge", "Most Popular");
        tractive.put("features", Arrays.asList("Worldwide Coverage", "Virtual Fence", "Live Tracking", "IPX7 Waterproof", "Activity Monitoring"));
        tractive.put("rating", 4.0);
        tractive.put("reviewCount", 312);
        tractive.put("description", "GPS pet tracker with worldwide coverage, real-time location tracking, and activity monitoring");
        tractive.put("bestFor", "International travelers with pets");
        
        // PitPat
        Map<String, Object> pitpat = new HashMap<>();
        pitpat.put("id", "pitpat");
        pitpat.put("name", "PitPat Dog Activity Monitor");
        pitpat.put("category", "Activity Monitor");
        pitpat.put("price", 39.99);
        pitpat.put("currency", "USD");
        pitpat.put("subscriptionInfo", "(No Subscription)");
        pitpat.put("imageUrl", "/images/pitpat.jpg");
        pitpat.put("badge", "Best Value");
        pitpat.put("features", Arrays.asList("1-Year Battery", "Activity Tracking", "Breed-Specific Goals", "Waterproof", "Lightweight (8g)"));
        pitpat.put("rating", 5.0);
        pitpat.put("reviewCount", 428);
        pitpat.put("description", "Lightweight dog activity monitor with 1-year battery life and breed-specific exercise goals");
        pitpat.put("bestFor", "Health-conscious dog owners on a budget");
        
        trackerList.add(pawfit);
        trackerList.add(tractive);
        trackerList.add(pitpat);
        
        return trackerList;
    }

    /**
     * Get a specific tracker by ID.
     *
     * @param id The tracker ID
     * @return The tracker if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTrackerById(@PathVariable String id) {
        Optional<Map<String, Object>> tracker = getAllTrackers().stream()
                .filter(t -> t.get("id").equals(id))
                .findFirst();
                
        return tracker.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get comparison data for all trackers
     * 
     * @return Comparison data for all trackers
     */
    @GetMapping("/comparison")
    public List<Map<String, Object>> getComparisonData() {
        // All data is already included in the getAllTrackers method
        return getAllTrackers();
    }
}

/**
 * Controller for tracker-related view pages.
 */
@Controller
@RequestMapping("/api/trackers/view")
class TrackerViewController {

    private final TrackerApiController apiController;

    public TrackerViewController(TrackerApiController apiController) {
        this.apiController = apiController;
    }

    /**
     * Display the trackers comparison page.
     *
     * @param model The model to add attributes to
     * @return The view name
     */
    @GetMapping("/comparison")
    public String showComparisonPage(Model model) {
        List<Map<String, Object>> trackers = apiController.getAllTrackers();
        model.addAttribute("trackers", trackers);
        return "accessories/trackers/comparison";
    }

    /**
     * Display a specific tracker's detail page.
     *
     * @param id The tracker ID
     * @param model The model to add attributes to
     * @return The view name or redirect if not found
     */
    @GetMapping("/{id}")
    public String showTrackerDetail(@PathVariable String id, Model model) {
        ResponseEntity<Map<String, Object>> response = apiController.getTrackerById(id);
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            model.addAttribute("tracker", response.getBody());
            
            // Return the specific template based on the tracker ID
            if ("pawfit-3".equals(id)) {
                return "accessories/trackers/pawfit-3";
            } else if ("tractive".equals(id)) {
                return "accessories/trackers/tractive";
            } else if ("pitpat".equals(id)) {
                return "accessories/trackers/pitpat";
            } else {
                // Generic detail page as fallback
                return "accessories/trackers/detail";
            }
        } else {
            return "redirect:/api/trackers/view/comparison";
        }
    }

    /**
     * Display the trackers listing page.
     *
     * @param model The model to add attributes to
     * @return The view name
     */
    @GetMapping
    public String showTrackersPage(Model model) {
        List<Map<String, Object>> trackers = apiController.getAllTrackers();
        model.addAttribute("trackers", trackers);
        return "accessories/trackers/index";
    }
}