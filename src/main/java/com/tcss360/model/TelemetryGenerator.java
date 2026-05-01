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

    /**  */
    private static final int ANOMALY_PERCENT = 31;

    /**
     * Private constructor to prevent instantiation
     */
    private TelemetryGenerator() {
    }

    /**
     * Generates a simulated telemetry stream. Random anomalous events occur
     * with as a percent chance defined by ANOMALY_PERCENT
     * @return array list of drone snapshots
     */
    public static ArrayList<DroneSnapshot> generateTelemetry(ArrayList<Drone> theDrones) {

        ArrayList<DroneSnapshot> theSnapshots = new ArrayList<>();
        Random rand = new Random(System.nanoTime());

        for (Drone drone : theDrones) {

            theSnapshots.add(new DroneSnapshot(drone)); // Always create the snapshot first
            int anomaly = rand.nextInt(1, 101);

            if (anomaly < ANOMALY_PERCENT) {

                int select = rand.nextInt(1, 5);

                switch (select) {
                    case 1 -> { // longitude change
                        double val = rand.nextDouble(0.25, 2.0);
                        drone.setLongitude(drone.getLongitude() + val);
                    }
                    case 2 -> { // latitude change
                        double val = rand.nextDouble(0.25, 2.0);
                        drone.setLatitude(drone.getLatitude() + val);
                    }
                    case 3 -> { // altitude change
                        double val = rand.nextDouble(0.25, 2.0);
                        drone.setAltitude(drone.getAltitude() + val);
                    }
                    case 4 -> { // heading change
                        double val = rand.nextDouble(45.0, 50.0);
                        int dir = rand.nextInt(2);
                        if (dir == 1) val *= -1;
                        drone.setHeading(normalizeHeading(drone.getHeading(), val));
                    }
                    default -> {
                        System.err.println("An unknown error occured durring anomalous telemetry generation.");
                    }
                }

            } else {

                // INSERT SCRIPTED LOGIC HERE

                /*
                 * Desired effect:
                 * drones fly directly right 10m
                 * drones will begin flying in a circle with r = 10m at speed 3 m/s
                 */

            }
        }

        return theSnapshots;
        
    }

    /**
     * A helper method used to normalize a heading ensuring it's value 
     * is always 0 thru 360 degrees
     * @param theCurrentHeading The current drone heading in degrees
     * @param theChange The change in heading in degrees
     * @return the normalized heading 0 through 360 degrees
     */
    private static double normalizeHeading(double theCurrentHeading, double theChange) {
        double heading = theCurrentHeading + theChange;
        return (heading % 360 + 360) % 360;
    }

}
