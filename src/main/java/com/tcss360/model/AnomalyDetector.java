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

            for (int i = 0; i < theDrones.size(); i++) {
                Drone drone = theDrones.get(i);
                DroneSnapshot snapshot = theDroneSnapshots.get(i);

                boolean lowBattery = checkLowBattery(drone, snapshot);
                boolean gpsSpoofing = checkGPSSpoofing(drone, snapshot);
                boolean unsafeMovement = checkUnsafeMovement(drone, snapshot);

                if (lowBattery) {
                    theRecords.add(createAnomalyRecord(drone.getID(),
                            "LOW_BATTERY",
                            "Battery at " + drone.getBatteryLevel() + "%"));
                }

                if (gpsSpoofing) {
                    theRecords.add(createAnomalyRecord(drone.getID(),
                            "GPS_SPOOFING",
                            "GPS jumped from (" + snapshot.getPreviousLatitude() + ", "
                                    + snapshot.getPreviousLongitude() + ") to ("
                                    + drone.getLatitude() + ", " + drone.getLongitude() + ")"));
                }

                if (unsafeMovement) {
                    theRecords.add(createAnomalyRecord(drone.getID(),
                            "UNSAFE_MOVEMENT",
                            "Heading changed from " + snapshot.getPreviousHeading()
                                    + " to " + drone.getHeading()));
                }

                if (checkCriticalAnomaly(lowBattery, gpsSpoofing, unsafeMovement)) {
                    theRecords.add(createAnomalyRecord(drone.getID(),
                            "CRITICAL_ANOMALY",
                            "Multiple anomalies detected at the same time."));
                }


            }

            return theRecords;
    }

    /**
     *Added low battery helper method in AnomalyDetector to detect battery threshold anomalies.
     * @param theDrone
     * @param theDroneSnapshot
     * @return
     */

    private boolean checkLowBattery(Drone theDrone, DroneSnapshot theDroneSnapshot) {
        return theDrone.getBatteryLevel() < myLowBatteryThreshold;
    }


    /**
     *
     * @param theDrone the current drone state
     * @param theDroneSnapshot the previous drone state
     * @return true if anomaly, false otherwise
     */

    private boolean checkGPSSpoofing(Drone theDrone,
        DroneSnapshot theDroneSnapshot) {

            double latChange = Math.abs(theDrone.getLatitude() - theDroneSnapshot.getPreviousLatitude());
            double lonChange = Math.abs(theDrone.getLongitude() - theDroneSnapshot.getPreviousLongitude());
            double altChange = Math.abs(theDrone.getAltitude() - theDroneSnapshot.getPreviousAltitude());
            return latChange > myGPSJumpThreshold || lonChange > myGPSJumpThreshold || altChange > myGPSJumpThreshold;
    }

    /**
     *
     * @param theDrone the current drone state
     * @param theDroneSnapshot the previous drone state
     * @return true if anomaly, false otherwise
     */
    private boolean checkUnsafeMovement(Drone theDrone,
        DroneSnapshot theDroneSnapshot) {

            double change = Math.abs(theDrone.getHeading() - theDroneSnapshot.getPreviousHeading());
            return change > myHeadingThreshold;
    }


    /**
     * Added advanced anomaly helper in AnomalyDetector to flag cases where multiple anomalies happen at the same time.
     * @param theLowBattery
     * @param theGPSSpoofing
     * @param theUnsafeMovement
     * @return
     */

    private boolean checkCriticalAnomaly(boolean theLowBattery,
                                         boolean theGPSSpoofing,
                                         boolean theUnsafeMovement) {
        int anomalyCount = 0;

        if (theLowBattery) {
            anomalyCount++;
        }
        if (theGPSSpoofing) {
            anomalyCount++;
        }
        if (theUnsafeMovement) {
            anomalyCount++;
        }

        return anomalyCount >= 2;
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

            return theRecord;
    }
}
