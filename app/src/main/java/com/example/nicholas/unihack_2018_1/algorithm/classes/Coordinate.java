package com.example.nicholas.unihack_2018_1.algorithm.classes;

/**
 * This represents a real coordinate.
 */
public class Coordinate {

    final float latitude;
    final float longitude;

    public Coordinate(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

}
