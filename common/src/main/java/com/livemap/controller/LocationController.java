package com.livemap.controller;

import com.livemap.model.Location;
import com.livemap.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/{userId}")
    public Location updateLocation(@PathVariable String userId, @RequestBody Map<String, Double> body) {
        double lat = body.get("latitude");
        double lng = body.get("longitude");
        return locationService.updateLocation(userId, lat, lng);
    }

    @GetMapping
    public Collection<Location> getAll() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{userId}")
    public Location getOne(@PathVariable String userId) {
        return locationService.getLocation(userId);
    }
}
