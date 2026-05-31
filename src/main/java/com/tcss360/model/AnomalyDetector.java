/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.model;

import java.util.ArrayList;

/**
 * Detects abnormal drone behavior by comparing current drone state and telemetry data
 * against configured anomaly thresholds.
 *
 * @author Logan Black
 * @version 28 APR 2026
 */
public class AnomalyDetector {

    /** Battery percentage threshold that indicates a low battery anomaly. */
    private final double myLowBatteryThreshold;

    /** Distance threshold for detecting suspicious GPS position jumps. */
    private final double myGPSJumpThreshold;

    /** Heading change threshold that indicates unsafe movement. */
    private final double myHeadingThreshold;

    /**
     * Detects anomalies for each drone using the current drone state and its telemetry snapshot.
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
     * Checks whether the drone battery level is below the configured threshold.
     *
     * @param theDrone the current drone state
     * @param theDroneSnapshot the telemetry snapshot associated with the drone
     * @return true if the drone has a low battery anomaly, otherwise false
     */

    private boolean checkLowBattery(Drone theDrone, DroneSnapshot theDroneSnapshot) {
        return theDrone.getBatteryLevel() < myLowBatteryThreshold;
    }


    /**
     * Checks whether the drone position shows a suspicious GPS jump compared to
     * the previous telemetry snapshot.
     *
     * @param theDrone the current drone state
     * @param theDroneSnapshot the telemetry snapshot associated with the drone
     * @return true if the drone shows a GPS jump anomaly, otherwise false
     */

    private boolean checkGPSSpoofing(Drone theDrone,
        DroneSnapshot theDroneSnapshot) {

            double latChange = Math.abs(theDrone.getLatitude() - theDroneSnapshot.getPreviousLatitude());
            double lonChange = Math.abs(theDrone.getLongitude() - theDroneSnapshot.getPreviousLongitude());
            double altChange = Math.abs(theDrone.getAltitude() - theDroneSnapshot.getPreviousAltitude());
            return latChange > myGPSJumpThreshold || lonChange > myGPSJumpThreshold || altChange > myGPSJumpThreshold;
    }

    /**
     * Checks whether the drone heading changed enough to be considered unsafe movement.
     *
     * @param theDrone the current drone state
     * @param theDroneSnapshot the telemetry snapshot associated with the drone
     * @return true if the drone shows an unsafe movement anomaly, otherwise false
     */
    private boolean checkUnsafeMovement(Drone theDrone,
        DroneSnapshot theDroneSnapshot) {

            double change = Math.abs(theDrone.getHeading() - theDroneSnapshot.getPreviousHeading());
            return change > myHeadingThreshold;
    }


    /**
     * Checks whether multiple anomalies occurred at the same time.
     *
     * @param theLowBattery whether a low battery anomaly was detected
     * @param theGPSSpoofing whether a GPS spoofing anomaly was detected
     * @param theUnsafeMovement whether an unsafe movement anomaly was detected
     * @return true if two or more anomalies were detected, otherwise false
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
     * Creates an anomaly record for a detected drone issue.
     *
     * @param theDroneID the ID of the drone associated with the anomaly
     * @param theAnomalyType the type of anomaly detected
     * @param theAnomalyDetails the details describing the anomaly
     * @return the created anomaly record
     */
    private AnomalyRecord createAnomalyRecord(int theDroneID,
        String theAnomalyType, String theAnomalyDetails) {

            AnomalyRecord theRecord = new AnomalyRecord(theDroneID,
                theAnomalyType, theAnomalyDetails);

            return theRecord;
    }
}
