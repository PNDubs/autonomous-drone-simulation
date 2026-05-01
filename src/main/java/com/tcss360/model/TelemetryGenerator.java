/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * The TelemetryGenerator is a utility class designed to simulate a
 * telemetry data stream.
 * @author Logan Black
 * @version 30 APR 2026
 */
public final class TelemetryGenerator {

    /**
     * Private constructor to prevent instantiation
     */
    private TelemetryGenerator() {
    }

    /**
     * Generates a simulated telemetry stream. Random anomalous events occur
     * with a 15% change under normal operations, and a 30% chance for testing.
     * @return array list of drone snapshots
     */
    public static ArrayList<DroneSnapshot> generateTelemetry(ArrayList<Drone> theDrones) {

        ArrayList<DroneSnapshot> theSnapshots = new ArrayList<>();
        Random rand = new Random(System.nanoTime());

        for (Drone drone : theDrones) {

            theSnapshots.add(new DroneSnapshot(drone));
            int anomaly = rand.nextInt(1, 101);

            if (anomaly > 70) {

                // INSERT ANOMALOUS LOGIC HERE

            } else {

                // INSERT SCRIPTED LOGIC HERE

            }
        }

        return theSnapshots;
        
    }
}
