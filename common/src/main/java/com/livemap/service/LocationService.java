package com.livemap.service;

import com.livemap.model.Location;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LocationService {
    private final ConcurrentHashMap<String, Location> locations = new ConcurrentHashMap<>();

    public Location updateLocation(String userId, double lat, double lng) {
        Location loc = new Location(userId, lat, lng);
        loc.setUpdatedAt(Instant.now());
        locations.put(userId, loc);
        return loc;
    }

    public Collection<Location> getAllLocations() {
        return locations.values();
    }

    public Location getLocation(String userId) {
        return locations.get(userId);
    }
}
