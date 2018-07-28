package com.example.nicholas.unihack_2018_1.algorithm.classes;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Intersection {
    private final String latitude;
    private final String longitude;
    private Map<String, Intersection> neighbors = new HashMap<>();

    public Intersection(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitudeAsFloat() {
        return Float.parseFloat(latitude);
    }

    public float getLongitudeAsFloat() {
        return Float.parseFloat(longitude);
    }

    public String getLatitudeAsString() {
        return latitude;
    }

    public String getLongitudeAsString() {
        return longitude;
    }

    public Intersection getNeighbor(String id) {
        return neighbors.get(id);
    }

    public Boolean hasNeighbor(String id) {
        return neighbors.containsKey(id);
    }

    public String getId() {
        final String alteredLatitude = latitude.replace(".", "");
        final String alteredLongitude = longitude.replace(".", "");
        return String.format(Locale.ENGLISH, "%s%s", alteredLatitude, alteredLongitude);
    }

    public void addNeighbor(String id, Intersection neighbor) {
        neighbors.put(id, neighbor);
    }

    public Intersection[] getNeighbors() {
        return (Intersection[]) neighbors.values().toArray();
    }
}