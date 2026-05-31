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

    /** Unique ID for this anomaly record. */
    private final UUID myRecordID;

    /** ID of the drone associated with this anomaly. */
    private final int myDroneID;

    /** Date and time when the anomaly record was created. */
    private final LocalDateTime myTimestamp;

    /** Type of anomaly that was detected. */
    private final String myAnomalyType;

    /** Details describing the detected anomaly. */
    private final String myAnomalyDetails;

    /**
     * Creates an anomaly record and automatically generates a unique record ID
     * and timestamp.
     *
     * @param theDroneID the ID of the drone associated with the anomaly
     * @param theAnomalyType the type of anomaly detected
     * @param theAnomalyDetails the details describing the anomaly
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
     * Creates an anomaly record with explicitly provided values.
     *
     * @param theRecordID the unique ID for the anomaly record
     * @param theDroneID the ID of the drone associated with the anomaly
     * @param theTimestamp the date and time of the anomaly
     * @param theAnomalyType the type of anomaly detected
     * @param theAnomalyDetails the details describing the anomaly
     */
    public AnomalyRecord(final UUID theRecordID,
                         final int theDroneID,
                         final LocalDateTime theTimestamp,
                         final String theAnomalyType,
                         final String theAnomalyDetails) {

        myRecordID = theRecordID;
        myDroneID = theDroneID;
        myTimestamp = theTimestamp;
        myAnomalyType = theAnomalyType;
        myAnomalyDetails = theAnomalyDetails;
    }
    /**
     * Returns the unique ID of this anomaly record.
     *
     * @return the anomaly record ID
     */
    public UUID getRecordID() {
        return myRecordID;
    }

    /**
     * Returns the ID of the drone associated with this anomaly.
     *
     * @return the drone ID
     */
    public int getDroneID() {
        return myDroneID;
    }

    /**
     * Returns the date and time of this anomaly record.
     *
     * @return the anomaly timestamp
     */
    public LocalDateTime getTimestap() {
        return myTimestamp;
    }

    /**
     * Returns the type of anomaly detected.
     *
     * @return the anomaly type
     */
    public String getAnomalyType() {
        return myAnomalyType;
    }

    /**
     * Returns the descriptive details of the anomaly.
     *
     * @return the anomaly details
     */
    public String getAnomalyDetails() {
        return myAnomalyDetails;
    }

    /**
     * Returns a readable string representation of this anomaly record.
     *
     * @return a formatted string describing the anomaly record
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("AnomalyRecord[");
        sb.append("RecordID=");
        sb.append(myRecordID);
        sb.append(", ");
        sb.append("DroneID=");
        sb.append(myDroneID);
        sb.append(", ");
        sb.append("Timestamp=");
        sb.append(myTimestamp);
        sb.append(", ");
        sb.append("AnomalyType=");
        sb.append(myAnomalyType);
        sb.append(", ");
        sb.append("AnomalyDetails=");
        sb.append(myAnomalyDetails);
        sb.append("]");
        
        return sb.toString();
    }
}
