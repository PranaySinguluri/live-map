package com.livemap.model;

import java.time.Instant;

public class Location {
    private String userId;
    private double latitude;
    private double longitude;
    private Instant updatedAt;

    public Location() {}

    public Location(String userId, double latitude, double longitude) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.updatedAt = Instant.now();
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
