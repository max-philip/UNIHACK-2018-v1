package com.example.nicholas.unihack_2018_1.algorithm;

import android.util.Log;

import com.example.nicholas.unihack_2018_1.algorithm.classes.Intersection;
import com.example.nicholas.unihack_2018_1.algorithm.classes.RoadInfo;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataParser {
    private static final String MAP_KEY = "map";
    private static final String ROADS_KEY = "roads";
    private static final String LATITUDE_KEY = "lat";
    private static final String LONGITUDE_KEY = "long";
    private static final String NEIGHBORS_KEY = "neighbours";


    public static Map<String, Intersection> intersections;
    public static Map<String, RoadInfo> roads;

    private DataParser() {
    }

    public static void readData(DataSnapshot snapshot) {
        readIntersections(snapshot);
        readRoads(snapshot);
    }

    private static void readIntersections(DataSnapshot snapshot) {
        intersections = new HashMap<>();

        // Grab list of points from snapshot.
        Iterable<DataSnapshot> points = snapshot.child("map").child("points").getChildren();

        // Iterate over points/intersections.
        int count = 0;
        while (points.iterator().hasNext()) {
            DataSnapshot nextIntersection = points.iterator().next();
            final String latitude = (String) nextIntersection.child(LATITUDE_KEY).getValue();
            final String  longitude = (String) nextIntersection.child(LONGITUDE_KEY).getValue();

            Intersection newIntesection = new Intersection(latitude, longitude);

            // Get list of neighbors.
            ArrayList<String> neigbors = (ArrayList<String>) nextIntersection.child(NEIGHBORS_KEY).getValue();
            for (String neighborId : neigbors) {

                if (intersections.containsKey(neighborId)) {
                    // We've already read in this neighbor and have an object for it. Let's add these
                    // two points as neighbors of one another.
                    intersections.get(neighborId).addNeighbor(newIntesection.getId(), newIntesection);
                    newIntesection.addNeighbor(neighborId, intersections.get(neighborId));
                }
            }

            intersections.put(newIntesection.getId(), newIntesection);
            count++;
            Log.d("Num of points read in:", Integer.toString(count));
        }

        Log.d("Finished!", "Yay");
    }

    private static void readRoads(DataSnapshot snapshot) {
        roads = new HashMap<>();

        // Grab list of points from snapshot.
        Iterable<DataSnapshot> roads = snapshot.child(MAP_KEY).child(ROADS_KEY).getChildren();

        // Iterate over points/intersections.
        int count = 0;
        while (roads.iterator().hasNext()) {
            DataSnapshot nextIntersection = roads.iterator().next();
    }
}
