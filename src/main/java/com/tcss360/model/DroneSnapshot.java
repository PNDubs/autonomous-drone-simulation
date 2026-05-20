/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.model;

/**
 * The DroneSnapshot class takes a snapshot of a drone object prior to mutation
 * by TelemetryGenerator so that AnomalyDetector can compare previous and
 * current state.
 * @author Logan Black
 * @version 28 APR 2026
 */
public class DroneSnapshot {

    /** The drones ID */
    private final int myID;

    /** The drones previous longitude */
    private final double myPreviousLongitude;

    /** The drones previous latitude */
    private final double myPreviousLatitude;

    /** The drones previous altitude */
    private final double myPreviousAltitude;

    /** The drones previous battery level */
    private final double myPreviousBatteryLevel;

    /** The drones previous heading */
    private final double myPreviousHeading;

    /** The drones previous speed */
    private final double myPreviousSpeed;

    /**
     * Constructor
     * @param theDrone the drone to take a snapshot of
     */
    public DroneSnapshot(Drone theDrone) {
        myID = theDrone.getID();
        myPreviousLongitude = theDrone.getLongitude();
        myPreviousLatitude = theDrone.getLatitude();
        myPreviousAltitude = theDrone.getAltitude();
        myPreviousBatteryLevel = theDrone.getBatteryLevel();
        myPreviousHeading = theDrone.getHeading();
        myPreviousSpeed = theDrone.getSpeed();
    }

    /**
     * Getter
     * @return the drones ID
     */
    public int getID() {
        return myID;
    }

    /**
     * Getter
     * @return the drones previous longitude
     */
    public double getPreviousLongitude() {
        return myPreviousLongitude;
    }

    /**
     * Getter
     * @return the drones previous latitude
     */
    public double getPreviousLatitude() {
        return myPreviousLatitude;
    }

    /**
     * Getter
     * @return the drones previous altitude
     */
    public double getPreviousAltitude() {
        return myPreviousAltitude;
    }

    /**
     * Getter
     * @return the drones previous battery level
     */
    public double getPreviousBatteryLevel() {
        return myPreviousBatteryLevel;
    }

    /**
     * Getter
     * @return the drones previous heading
     */
    public double getPreviousHeading() {
        return myPreviousHeading;
    }

    /**
     * Getter
     * @return the drones previous speed
     */
    public double getPreviousSpeed() {
        return myPreviousSpeed;
    }
}
