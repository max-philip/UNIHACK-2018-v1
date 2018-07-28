package com.example.nicholas.unihack_2018_1.algorithm;


import com.example.nicholas.unihack_2018_1.algorithm.classes.Coordinate;
import com.example.nicholas.unihack_2018_1.algorithm.classes.Intersection;
import com.example.nicholas.unihack_2018_1.algorithm.classes.RoadInfo;

public class AStar {

//    public double getCost(Intersection i1, Intersection i2) {
//
//    }

    /**
     * Calculates the raw euclidean distance between two coordinates
     * @return
     */
    public double rawDist(Coordinate p1, Coordinate p2) {
        final double earthRadiusKm = 6371;

        double dLat = degToRad(p2.getLatitude()-p1.getLatitude());
        double dLon = degToRad(p2.getLongitude()-p1.getLongitude());

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.sin(dLon/2) * Math.sin(dLon/2) *
                    Math.cos(degToRad(p1.getLatitude())) * Math.cos(degToRad(p2.getLatitude()));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return earthRadiusKm * c;

    }

    public double degToRad(float degrees) {
        return degrees * Math.PI / 180;
    }

}
