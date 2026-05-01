/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.model;

import java.util.ArrayList;

/**
 * The AnomalyDetector class compares a drones current state to its previous
 * state to check for anomalous behaviour.
 * @author Logan Black
 * @version 28 APR 2026
 */
public class AnomalyDetector {

    /** The battery level that indicates an anomaly */
    private final double myLowBatteryThreshold;

    /** The change in longitude, latitude, or altitude that indicates an anomaly */
    private final double myGPSJumpThreshold;

    /** The change in heading that indicates an anomaly */
    private final double myHeadingThreshold;

    /**
     * Constructor
     * @param theLowBatteryThreshold the battery level indicating an anomaly
     * @param theAltitudeThreshold the change in altitude indicating an anomaly
     * @param theGPSJumpThreshold the change in longitude or latitude indicating an anomaly
     * @param theHeadingThreshold the change in heading indicating an anomaly
     */
    public AnomalyDetector(double theLowBatteryThreshold, 
        double theGPSJumpThreshold, double theHeadingThreshold) {

            myLowBatteryThreshold = theLowBatteryThreshold;
            myGPSJumpThreshold = theGPSJumpThreshold;
            myHeadingThreshold = theHeadingThreshold;
    }

    /**
     * 
     * @return array list of anomaly records
     */
    public ArrayList<AnomalyRecord> detectAnomalies(ArrayList<Drone> theDrones, 
        ArrayList<DroneSnapshot> theDroneSnapshots) {
            
            ArrayList<AnomalyRecord> theRecords = new ArrayList<>();

            /* Implement logic here */

            return theRecords;
    }

    /**
     * 
     * @param theDrone the current drone state
     * @param theDroneSnapshot the previous drone state
     * @return true if anomaly, false otherwise
     */
    private boolean checkLowBattery(Drone theDrone, 
        DroneSnapshot theDroneSnapshot) {

            boolean isAnomaly = false;

            /* Implement logic here */

            return isAnomaly;
    }

    /**
     * 
     * @param theDrone the current drone state
     * @param theDroneSnapshot the previous drone state
     * @return true if anomaly, false otherwise
     */
    private boolean checkGPSSpoofing(Drone theDrone,
        DroneSnapshot theDroneSnapshot) {

            boolean isAnomaly = false;

            /* Implement logic here */

            return isAnomaly;
    }

    /**
     * 
     * @param theDrone the current drone state
     * @param theDroneSnapshot the previous drone state
     * @return true if anomaly, false otherwise
     */
    private boolean checkUnsafeMovement(Drone theDrone, 
        DroneSnapshot theDroneSnapshot) {

            boolean isAnomaly = false;

            /* Implement logic here */

            return isAnomaly;
    }

    /**
     * 
     * @param theDroneID the drones ID
     * @param theAnomalyType the type of anomaly experienced
     * @param theAnomalyDetails the details of the anomaly
     * @return the anomaly record
     */
    private AnomalyRecord createAnomalyRecord(int theDroneID, 
        String theAnomalyType, String theAnomalyDetails) {

            AnomalyRecord theRecord = new AnomalyRecord(theDroneID, 
                theAnomalyType, theAnomalyDetails);

            /* Implement logic here */

            return theRecord;
    }
}
