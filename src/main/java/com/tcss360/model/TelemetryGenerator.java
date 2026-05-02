/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * The TelemetryGenerator is designed to simulate a telemetry data stream.
 * @author Logan Black
 * @version 01 May 2026
 */
public final class TelemetryGenerator {

    /** The percent chance of an anomaly to occur */
    private static final int ANOMALY_PERCENT = 30;

    /** The number of ticks per second */
    private static final double TICKS_PER_SECOND = 4;

    /** The default straight travel distance for scripted drone travel */
    private static final double STRAIGHT_DISTANCE = 10.0;

    /** The default circle radius for scripted drone travel */
    private static final double SCRIPTED_RADIUS = 10.0;

    /** The tick count state for telemetry generation */
    private int myTickCount;

    /**
     * Constructor
     */
    public TelemetryGenerator() {
        myTickCount = 0;
    }

    /**
     * Generates a simulated telemetry stream. Random anomalous events occur
     * with as a percent chance defined by ANOMALY_PERCENT
     * @return array list of drone snapshots
     */
    public ArrayList<DroneSnapshot> generateTelemetry(ArrayList<Drone> theDrones) {

        ArrayList<DroneSnapshot> theSnapshots = new ArrayList<>();
        Random rand = new Random(System.nanoTime());

        for (Drone drone : theDrones) {

            theSnapshots.add(new DroneSnapshot(drone)); // Always create the snapshot first

            int anomaly = rand.nextInt(0, 100);

            if (anomaly < ANOMALY_PERCENT) applyAnomaly(rand, drone); // possible anomaly applied to this scripted update
            
            applyScriptedMovement(drone); // apply the scripted update

        }

        myTickCount++;

        return theSnapshots;

    }

    /**
     * A helper method used to normalize a heading ensuring it's value 
     * is always 0 thru 360 degrees
     * @param theCurrentHeading The current drone heading in degrees
     * @param theChange The change in heading in degrees
     * @return the normalized heading 0 through 360 degrees
     */
    private double normalizeHeading(double theCurrentHeading, double theChange) {
        double heading = theCurrentHeading + theChange;
        return (heading % 360 + 360) % 360;
    }

    /**
     * A helper method used to apply random anomalies to drones.
     * @param theRandom random object used to anomaly selection
     * @param theDrone the drone receiving the anomaly
     */
    private void applyAnomaly(Random theRandom, Drone theDrone) {

        int select = theRandom.nextInt(1, 5);

        switch (select) {
            case 1 -> { // longitude change
                double val = theRandom.nextDouble(0.25, 2.0);
                theDrone.setLongitude(theDrone.getLongitude() + val);
            }
            case 2 -> { // latitude change
                double val = theRandom.nextDouble(0.25, 2.0);
                theDrone.setLatitude(theDrone.getLatitude() + val);
            }
            case 3 -> { // altitude change
                double val = theRandom.nextDouble(0.25, 2.0);
                theDrone.setAltitude(theDrone.getAltitude() + val);
            }
            case 4 -> { // heading change
                double val = theRandom.nextDouble(45.0, 50.0);
                int dir = theRandom.nextInt(2);
                if (dir == 1) val *= -1;
                theDrone.setHeading(normalizeHeading(theDrone.getHeading(), val));
            }
            default -> {
                System.err.println("An unknown error occured durring anomalous telemetry generation.");
            }
        }
    }

    /**
     * This method is designed to apply scripted movement to each drone.
     * @param theDrone the drone being updated
     */
    public void applyScriptedMovement(Drone theDrone) {

        /*
         * If no anomalies occur, the drone should travel in an approximated 
         * circle. If an anomaly does occur, the drone will apply the next 
         * set of movement instructions with no correction. This design choice 
         * is explicit such that anomalies can be seen in real time. e.g. if 
         * an anomaly changes the heading of the drone, the script will 
         * continue from that new heading.
         * 
         * A future update may use an updated formula for movement which requires
         * a fixed center to produce a mathematically perfect circle. That
         * approach would require storing each drone's start position and circle
         * center.
         * 
         * To ensure the persistence of anomalies, latitude and longitude anomalies
         * should be tracked as offsets from the scripted path. Recalculating
         * position without the offsets would result in the drone 'snapping back'
         * to the correct path on the next update.
         * 
         * Another future update may include corrective measures that visualize
         * the drone returning to its correct path after an anomaly.
         */

        double distancePerTick = theDrone.getSpeed() / TICKS_PER_SECOND;
        int straightTicks = (int) Math.round(STRAIGHT_DISTANCE / distancePerTick);

        double headingRadians = Math.toRadians(theDrone.getHeading());
        double longitudeChange;
        double latitudeChange;

        if (myTickCount < straightTicks) {

            longitudeChange = distancePerTick * Math.cos(headingRadians);
            latitudeChange = distancePerTick * Math.sin(headingRadians);

        } else {

            double headingChange = Math.toDegrees(distancePerTick / SCRIPTED_RADIUS);

            theDrone.setHeading(normalizeHeading(theDrone.getHeading(), headingChange));

            headingRadians = Math.toRadians(theDrone.getHeading());
            longitudeChange = distancePerTick * Math.cos(headingRadians);
            latitudeChange = distancePerTick * Math.sin(headingRadians);
        }

        theDrone.setLongitude(theDrone.getLongitude() + longitudeChange);
        theDrone.setLatitude(theDrone.getLatitude() + latitudeChange);
    }
}
