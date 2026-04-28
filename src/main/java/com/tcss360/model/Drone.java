/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.model;

/**
 * The Drone class represents simulated drone objects and contains the
 * required identifying information in its fields.
 * @author Logan Black
 * @version 28 APR 2026
 */
public class Drone {

    /** The ID of the Drone */
    int myID;
    
    /** The current longitude of the Drone */
    double myLongitude;

    /** The current latitude of the Drone */
    double myLatitude;

    /** The current altitude of the Drone */
    double myAltitude;

    /** The current battery level of the Drone */
    int myBatteryLevel;

    /** The current heading of the Drone */
    double myHeading;

    /** The current speed of the Drone */
    double mySpeed;

    /**
     * Constructor
     * @param theID the drones ID
     * @param theLongitude the drones longitude
     * @param theLatitude the drones latitude
     * @param theAltitude the drones altitude
     * @param theBatteryLevel the drones battery level
     * @param theHeading the drones heading
     * @param theSpeed the drones speed
     */
    public Drone(int theID, double theLongitude, double theLatitude, double 
        theAltitude, int theBatteryLevel, double theHeading, double theSpeed) {

            myID= theID;
            myLongitude = theLongitude;
            myLatitude = theLatitude;
            myAltitude = theAltitude;
            myBatteryLevel = theBatteryLevel;
            myHeading = theHeading;
            mySpeed = theSpeed;
    }

    /**
     * Getter
     * @return the Drone ID
     */
    public int getID() {
        return myID;
    }

    /**
     * Getter
     * @return the Drone longitude
     */
    public double getLongitude() {
        return myLongitude;
    }
    
    /**
     * Getter
     * @return the Drone latitude
     */
    public double getLatitude() {
        return myLatitude;
    }
    
    /**
     * Getter
     * @return the Drone altitude
     */
    public double getAltitude() {
        return myAltitude;
    }

    /**
     * Getter
     * @return the Drone battery level
     */
    public int getBatteryLevel() {
        return myBatteryLevel;
    }

    /**
     * Getter
     * @return the Drone heading
     */
    public double getHeading() {
        return myHeading;
    }

    /**
     * Getter
     * @return the Drone speed
     */
    public double getSpeed() {
        return mySpeed;
    }

    /**
     * Sets the drones longitude
     * @param theLongitude the drones current longitude
     */
    public void setLongitude(double theLongitude) {
        myLongitude = theLongitude;
    }

    /**
     * Sets the drones latitude
     * @param theLatitude the drones current latitude
     */
    public void setLatitude(double theLatitude) {
        myLatitude = theLatitude;
    }

    /**
     * Sets the drones altitude
     * @param theAltitude the drones current altitude
     */
    public void setAltitude(double theAltitude) {
        myAltitude = theAltitude;
    }

    /**
     * Sets the drones battery level
     * @param theBatteryLevel the drones current battery level
     */
    public void setBatteryLevel(int theBatteryLevel) {
        myBatteryLevel = theBatteryLevel;
    }

    /**
     * Sets the drones heading
     * @param theHeading the drones current heading
     */
    public void setHeading(double theHeading) {
        myHeading = theHeading;
    }

    /**
     * Sets the drones speed
     * @param theSpeed the drones current speed
     */
    public void setSpeed(double theSpeed) {
        mySpeed = theSpeed;
    }
}
