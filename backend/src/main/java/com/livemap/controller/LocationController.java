package com.livemap.controller;

import com.livemap.model.Location;
import com.livemap.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private LocationService locationService;

    private static final ConcurrentHashMap<String, String> accessCodeToUser = new ConcurrentHashMap<>();

    @PostMapping("/locations/{accessCode}")
    public Map<String, String> updateLocation(@PathVariable String accessCode, @RequestBody Map<String, Object> body) {
        String userId = (String) body.get("userId");
        Double latitude = (Double) body.get("latitude");
        Double longitude = (Double) body.get("longitude");

        Map<String, String> response = new HashMap<>();

        if (accessCode == null || accessCode.length() != 4 || userId == null) {
            response.put("error", "Invalid access code or user");
            return response;
        }

        // Store the mapping of access code to user name
        accessCodeToUser.put(accessCode, userId);

        // Store location with access code as ID (for privacy)
        locationService.updateLocation(accessCode, latitude, longitude);

        response.put("success", "true");
        response.put("userId", userId);
        return response;
    }

    @GetMapping("/partner/{yourAccessCode}/{partnerAccessCode}")
    public Map<String, Object> getPartnerLocation(@PathVariable String yourAccessCode, @PathVariable String partnerAccessCode) {
        Map<String, Object> response = new HashMap<>();

        if (yourAccessCode == null || partnerAccessCode == null ||
                yourAccessCode.length() != 4 || partnerAccessCode.length() != 4) {
            response.put("error", "Invalid access code format");
            return response;
        }

        if (yourAccessCode.equals(partnerAccessCode)) {
            response.put("error", "Cannot view your own location");
            return response;
        }

        Location loc = locationService.getLocation(partnerAccessCode);
        if (loc == null) {
            response.put("error", "Partner location not found");
            return response;
        }

        String partnerName = accessCodeToUser.getOrDefault(partnerAccessCode, "Unknown");

        response.put("success", true);
        response.put("userId", partnerName);
        response.put("latitude", loc.getLatitude());
        response.put("longitude", loc.getLongitude());
        response.put("updatedAt", loc.getUpdatedAt());
        return response;
    }
}