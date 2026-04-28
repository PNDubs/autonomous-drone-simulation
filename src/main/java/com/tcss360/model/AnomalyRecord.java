/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The AnomalyRecord class stores anomaly information in it's fields
 * @author Logan Black
 * @version 28 APR 2026
 */
public class AnomalyRecord {

    /** The anomaly record ID */
    private final UUID myRecordID;

    /** The drone ID */
    private final int myDroneID;

    /** The date and time of the anomaly */
    private final LocalDateTime myTimestamp;

    /** The anomaly type */
    private final String myAnomalyType;

    /** The anomaly details */
    private final String myAnomalyDetails;

    /**
     * Constructor
     * @param theDroneID The drones ID
     * @param theAnomalyType the anomaly type
     * @param theAnomalyDetails the anomaly details
     */
    public AnomalyRecord(int theDroneID, 
        String theAnomalyType, String theAnomalyDetails) {

            myRecordID = UUID.randomUUID();
            myDroneID = theDroneID;
            myTimestamp = LocalDateTime.now();
            myAnomalyType = theAnomalyType;
            myAnomalyDetails = theAnomalyDetails;

    }

    /**
     * Getter
     * @return the record ID
     */
    public UUID getRecordID() {
        return myRecordID;
    }

    /**
     * Getter
     * @return the drone ID
     */
    public int getDroneID() {
        return myDroneID;
    }

    /**
     * Getter
     * @return the anomaly date and time
     */
    public LocalDateTime getTimestap() {
        return myTimestamp;
    }

    /**
     * Getter
     * @return the anomaly type
     */
    public String getAnomalyType() {
        return myAnomalyType;
    }

    /**
     * Getter
     * @return the anomaly details
     */
    public String getAnomalyDetails() {
        return myAnomalyDetails;
    }

    /**
     * Overrides the default toString() method
     * @return the anomaly record in a print friendly format
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        /* Implement logic here */

        return sb.toString();
    }
}
