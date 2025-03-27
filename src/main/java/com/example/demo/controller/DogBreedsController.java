package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DogBreedsController {

    @GetMapping("/dog-breeds")
    public String getDogBreedsHomePage() {
        return "/blog/dog-breeds/dog-breeds";
    }
    //test
    @GetMapping("/dog-breeds/{slug}")
    public String getDogBreedPage(@PathVariable String slug) {
        switch (slug) {
            case "golden-retriever-guide":
                return "dog-breeds/golden-retriever-guide";
            case "labrador-guide":
                return "dog-breeds/labrador-guide";
            case "german-shepherd-guide":
                return "dog-breeds/german-shepherd-guide";
            case "husky-guide":
                return "dog-breeds/husky-guide";
            case "poodle-guide":
                return "dog-breeds/poodle-guide";
            case "bulldog-guide":
                return "dog-breeds/bulldog-guide";
            case "chihuahua-guide":
                return "dog-breeds/chihuahua-guide";
            case "corgi-guide":
                return "dog-breeds/corgi-guide";
            case "beagle-guide":
                return "dog-breeds/beagle-guide";
            case "samoyed-guide":
                return "dog-breeds/samoyed-guide";
            case "shiba-inu-guide":
                return "dog-breeds/shiba-inu-guide";
            case "border-collie-guide":
                return "dog-breeds/border-collie-guide";
            default:
                return "redirect:/dog-breeds";
        }
    }
}
