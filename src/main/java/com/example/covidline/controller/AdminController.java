package com.example.covidline.controller;

import com.example.covidline.constant.PlaceType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping("/places")
    public ModelAndView adminPlaces(
            @RequestParam(required = false) PlaceType placeType,
            String placeName,
            String address
            ) {
        Map<String, Object> map = new HashMap<>();
        map.put("placeType", placeType);
        map.put("placeName", placeName);
        map.put("address", address);
        return new ModelAndView("admin/places", map);
    }

    @GetMapping("/places/{placeId}")
    public String adminPlaceDetail(@PathVariable Integer placeId) {
        return "admin/place-detail";
    }

    @GetMapping("/events")
    public String adminEvents() {
        return "admin/events";
    }

    @GetMapping("/events/{eventId}")
    public String adminEventDetail(@PathVariable Integer eventId) {
        return "admin/event-detail";
    }
}
