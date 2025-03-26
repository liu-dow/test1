package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Controller for tracker-related web pages.
 */
@Controller
@RequestMapping("/accessories/trackers")
public class TrackerController {

    /**
     * Display the trackers comparison page.
     *
     * @param model The model to add attributes to
     * @return The view name
     */
    @GetMapping("/comparison")
    public String showComparisonPage(Model model) {
        List<Map<String, Object>> trackers = getAllTrackers();
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
        return getTrackerById(id)
                .map(tracker -> {
                    model.addAttribute("tracker", tracker);
                    return "accessories/trackers/detail";
                })
                .orElse("redirect:/accessories/trackers");
    }

    /**
     * Display the trackers listing page.
     *
     * @param model The model to add attributes to
     * @return The view name
     */
    @GetMapping
    public String showTrackersPage(Model model) {
        List<Map<String, Object>> trackers = getAllTrackers();
        model.addAttribute("trackers", trackers);
        return "accessories/trackers/index";
    }
    
    /**
     * Get all available trackers.
     * 
     * @return List of all trackers
     */
    private List<Map<String, Object>> getAllTrackers() {
        List<Map<String, Object>> trackerList = new ArrayList<>();
        
        // Pawfit 3
        Map<String, Object> pawfit = new HashMap<>();
        pawfit.put("id", "pawfit-3");
        pawfit.put("name", "Pawfit 3");
        pawfit.put("category", "GPS & Activity Tracker");
        pawfit.put("price", "£49.99");
        pawfit.put("subscriptionInfo", "+ Subscription");
        pawfit.put("imageUrl", "/images/pawfit-3.jpg");
        pawfit.put("badge", "Best Overall");
        pawfit.put("features", Arrays.asList("7-Day Battery", "Real-time GPS", "Temperature Alerts"));
        pawfit.put("rating", 4.5);
        pawfit.put("reviewCount", 256);
        pawfit.put("detailUrl", "/api/trackers/pawfit-3");
        pawfit.put("description", "The Pawfit 3 represents the latest evolution in pet tracking technology, combining precise GPS location monitoring with comprehensive health and activity tracking features.");
        pawfit.put("bestFor", "Medium to large dogs requiring reliable tracking");
        
        // Tractive GPS
        Map<String, Object> tractive = new HashMap<>();
        tractive.put("id", "tractive");
        tractive.put("name", "Tractive GPS");
        tractive.put("category", "GPS Tracker");
        tractive.put("price", "£44.99");
        tractive.put("subscriptionInfo", "+ Subscription");
        tractive.put("imageUrl", "/images/tractive.jpg");
        tractive.put("badge", "Most Popular");
        tractive.put("features", Arrays.asList("Worldwide Coverage", "Virtual Fence", "Live Tracking"));
        tractive.put("rating", 4.0);
        tractive.put("reviewCount", 312);
        tractive.put("detailUrl", "/api/trackers/tractive");
        tractive.put("description", "Tractive GPS offers worldwide coverage with no distance limits, making it ideal for pets that travel with their owners.");
        tractive.put("bestFor", "International travelers with pets");
        
        // PitPat
        Map<String, Object> pitpat = new HashMap<>();
        pitpat.put("id", "pitpat");
        pitpat.put("name", "PitPat");
        pitpat.put("category", "Activity Monitor");
        pitpat.put("price", "£39.99");
        pitpat.put("subscriptionInfo", "(No Subscription)");
        pitpat.put("imageUrl", "/images/pitpat.jpg");
        pitpat.put("badge", "Best Value");
        pitpat.put("features", Arrays.asList("1-Year Battery", "Activity Tracking", "Breed-Specific Goals"));
        pitpat.put("rating", 5.0);
        pitpat.put("reviewCount", 428);
        pitpat.put("detailUrl", "/api/trackers/pitpat");
        pitpat.put("description", "PitPat is a lightweight activity monitor that tracks your dog's exercise, rest, and play throughout the day with breed-specific goals.");
        pitpat.put("bestFor", "Health-conscious dog owners on a budget");
        
        trackerList.add(pawfit);
        trackerList.add(tractive);
        trackerList.add(pitpat);
        
        return trackerList;
    }
    
    /**
     * Find a tracker by its ID.
     * 
     * @param id The tracker ID
     * @return Optional containing the tracker if found
     */
    private Optional<Map<String, Object>> getTrackerById(String id) {
        return getAllTrackers().stream()
                .filter(tracker -> tracker.get("id").equals(id))
                .findFirst();
    }
}