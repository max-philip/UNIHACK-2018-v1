package com.example.nicholas.unihack_2018_1.algorithm;


import com.example.nicholas.unihack_2018_1.algorithm.classes.Coordinate;
import com.example.nicholas.unihack_2018_1.algorithm.classes.Intersection;
import com.example.nicholas.unihack_2018_1.algorithm.classes.RoadInfo;

public class AStar {

    /** Keep unsafety scalar between 1-20 to prevent less than 0 hcost **/
    private static double UNSAFETY_SCALAR = 0;
    private static double HCOST_CONSTANT = 100;

//    public double getFCost(Intersection i1, Intersection i2) {
//
//
//
//    }

    public double getHCost(Intersection i1, Intersection i2, Intersection dest) {

        RoadInfo roadInfo = DataParser.roads.get(generateRoadID(i1, i2));

        double dist = getDistanceAsMetres(i1.getCoordinate(), dest.getCoordinate());
        return HCOST_CONSTANT + dist + UNSAFETY_SCALAR * (roadInfo.crime + roadInfo.parks - roadInfo.safety - roadInfo.lights);

    }



    /**
     * Generate our RoadID from two intersects
     * @param i1
     * @param i2
     * @return
     */
    private String generateRoadID(Intersection i1, Intersection i2) {

        return (i1.getId().compareTo(i2.getId()) > 0 ? i2.getId() + i1.getId() : i1.getId() + i2.getId());

    }

    /**
     * Finds the closest intersection to the provided coordinate
     * @param coord
     * @return
     */
    public Intersection getClosestIntersection(Coordinate coord) {

        double shortest = Double.MAX_VALUE;
        Intersection closestIntersection = null;

        for (Intersection intersection : DataParser.intersections.values()) {
            double dist = getDistanceAsMetres(intersection.getCoordinate(), coord);
            if (dist < shortest) {
                closestIntersection = intersection;
                shortest = dist;
            }
        }

        return closestIntersection;

    }

    /**
     * Calculates the raw euclidean distance between two coordinates in Metres
     * @return
     */
    public static double getDistanceAsMetres(Coordinate p1, Coordinate p2) {
        final double earthRadiusM = 6371000;

        double dLat = degToRad(p2.getLatitude()-p1.getLatitude());
        double dLon = degToRad(p2.getLongitude()-p1.getLongitude());

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.sin(dLon/2) * Math.sin(dLon/2) *
                    Math.cos(degToRad(p1.getLatitude())) * Math.cos(degToRad(p2.getLatitude()));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return earthRadiusM * c;

    }

    public static double degToRad(double degrees) {
        return degrees * Math.PI / 180;
    }

}
