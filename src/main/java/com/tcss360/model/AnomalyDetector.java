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
 * @version 15 May 2026
 */
public class AnomalyDetector {

    private static final double GPS_JUMP_TOLERANCE = 0.00001;

    /** The battery level that indicates an anomaly */
    private final double myLowBatteryThreshold;

    /** The change in longitude, latitude, or altitude that indicates an anomaly */
    private final double myGPSJumpThreshold;

    /** The change in heading that indicates an anomaly */
    private final double myHeadingThreshold;

    /**
     * Constructor
     * @param theLowBatteryThreshold the battery level indicating an anomaly
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
     * Detects anomalies in drones and facilitates the creation of anomaly records
     * @return array list of anomaly records
     */
    public ArrayList<AnomalyRecord> detectAnomalies(ArrayList<Drone> theDrones,
        ArrayList<DroneSnapshot> theDroneSnapshots) {

            ArrayList<AnomalyRecord> theRecords = new ArrayList<>();

            for (int i = 0; i < theDrones.size(); i++) {
                Drone drone = theDrones.get(i);
                DroneSnapshot snapshot = theDroneSnapshots.get(i);

                if (checkLowBattery(drone)) {
                    theRecords.add(createAnomalyRecord(drone.getID(),
                        "LOW_BATTERY",
                        "Battery at " + drone.getBatteryLevel() + "%"));
                }
                if (checkGPSSpoofing(drone, snapshot)) {
                    theRecords.add(createAnomalyRecord(drone.getID(),
                        "GPS_SPOOFING",
                        "GPS jumped from (" + snapshot.getPreviousLatitude() + ", "
                        + snapshot.getPreviousLongitude() + ") to ("
                        + drone.getLatitude() + ", " + drone.getLongitude() + ")"));
                }
                if (checkUnsafeMovement(drone, snapshot)) {
                    theRecords.add(createAnomalyRecord(drone.getID(),
                        "UNSAFE_MOVEMENT",
                        "Heading changed from " + snapshot.getPreviousHeading()
                        + " to " + drone.getHeading()));
                }
            }

            return theRecords;
    }

    /**
     * Checks if the latitude, longitude, or altitude change is within the set threshold
     * @param theDrone the current drone state
     * @param theDroneSnapshot the previous drone state
     * @return true if anomaly, false otherwise
     */
    private boolean checkGPSSpoofing(Drone theDrone,
        DroneSnapshot theDroneSnapshot) {

            double latChange = theDrone.getLatitude() - theDroneSnapshot.getPreviousLatitude();
            double lonChange = theDrone.getLongitude() - theDroneSnapshot.getPreviousLongitude();
            double horizontalChange = Math.sqrt(latChange * latChange + lonChange * lonChange);
            double altChange = Math.abs(theDrone.getAltitude() - theDroneSnapshot.getPreviousAltitude());
            return horizontalChange > myGPSJumpThreshold + GPS_JUMP_TOLERANCE 
                || altChange > myGPSJumpThreshold + GPS_JUMP_TOLERANCE;
    }

    /**
     * Checks for unsafe movement based on heading changes over time
     * @param theDrone the current drone state
     * @param theDroneSnapshot the previous drone state
     * @return true if anomaly, false otherwise
     */
    private boolean checkUnsafeMovement(Drone theDrone,
        DroneSnapshot theDroneSnapshot) {

            double rawChange = Math.abs(theDrone.getHeading() - 
                theDroneSnapshot.getPreviousHeading());
            double change = Math.min(rawChange, 360.0 - rawChange);
            return change > myHeadingThreshold;
    }

    /**
     * Creates anomaly record objects
     * @param theDroneID the drones ID
     * @param theAnomalyType the type of anomaly experienced
     * @param theAnomalyDetails the details of the anomaly
     * @return the anomaly record
     */
    private AnomalyRecord createAnomalyRecord(int theDroneID,
        String theAnomalyType, String theAnomalyDetails) {

            AnomalyRecord theRecord = new AnomalyRecord(theDroneID,
                theAnomalyType, theAnomalyDetails);

            return theRecord;
    }

    /**
     * Checks if the drone battery level is below the low battery threshold.
     * @param theDrone the current drone state
     * @param theDroneSnapshot the previous drone state
     * @return true if low battery, false otherwise
     */
    private boolean checkLowBattery(Drone theDrone) {
        return theDrone.getBatteryLevel() < myLowBatteryThreshold;
    }
}
