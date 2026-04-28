/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.controller;

import java.util.ArrayList;
import java.util.Timer;

import com.tcss360.model.AnomalyDatabase;
import com.tcss360.model.AnomalyDetector;
import com.tcss360.model.AnomalyRecord;
import com.tcss360.model.Drone;
import com.tcss360.model.DroneSnapshot;
import com.tcss360.model.TelemetryGenerator;
import com.tcss360.view.MonitorDashboard;

/**
 * The DroneMonitorApp class is the main controller for the autonomous
 * drone simulation program
 * @author Logan Black
 * @version 28 APR 2026
 */
public class DroneMonitorApp {

    /** The low battery anomaly threshold */
    private static final int LOW_BATTERY_THRESHOLD = 15;

    /** The altitude anomaly threshold */
    private static final double ALTITUDE_THRESHOLD = 0.0; // INSERT VALUE HERE

    //** The longitude or latitude anomlay threshold */
    private static final double GPS_JUMP_THRESHOLD = 0.0; // INSERT VALUE HERE

    /** The heading threshold */
    private static final double HEADING_THRESHOLD = 0.0; // INSERT VALUE HERE

    /** The drone fleet */
    private final ArrayList<Drone> myDrones;

    /** The current drone snapshots for anomaly detection */
    private ArrayList<DroneSnapshot> myDroneSnapshots;

    /** The anomaly detector */
    private final AnomalyDetector myAnomalyDetector;

    /** The anomaly database class */
    private final AnomalyDatabase myAnomalyDatabase;

    /** The monitor dashboard */
    private MonitorDashboard myMonitorDashboard;

    /** The system telemetry generation timer */
    private Timer myUpdateTimer;

    public DroneMonitorApp() {
        myDrones = initializeDrones();
        myDroneSnapshots = new ArrayList<>();
        myAnomalyDetector = new AnomalyDetector(LOW_BATTERY_THRESHOLD, 
            HEADING_THRESHOLD, GPS_JUMP_THRESHOLD, ALTITUDE_THRESHOLD);
        myMonitorDashboard = initializeMonitorDashboard();
        myAnomalyDatabase = new AnomalyDatabase();
        myUpdateTimer = new Timer();
    }

    /**
     * The method used to start the drone monitor app controller
     */
    public void start() {

        /* Insert logic here */

    }

    /**
     * Helper method used to initialize the drones
     * @return a list of drones
     */
    private ArrayList<Drone> initializeDrones() {

        /* Insert logic here */

        return null;
    }

    /**
     * Helper method to initialize the monitor dashboard
     */
    private MonitorDashboard initializeMonitorDashboard() {

        /* Insert logic here */

        return null;
    }

    /**
     * Helper method used to simulate telemetry stream
     * @return a list of previous drone states
     */
    private void updateTelemetry() {
        myDroneSnapshots = TelemetryGenerator.generateTelemetry();
    }

    /**
     * Helper method used to check for anomalies
     * @return
     */
    private void checkForAnomalies() {
        ArrayList<AnomalyRecord> theRecords = myAnomalyDetector.
            detectAnomalies(myDrones, myDroneSnapshots);

        if (theRecords.size() >= 1) {
            saveAnomalies(theRecords);
        }
    }

    /**
     * Helper method to store anomaly records
     */
    private void saveAnomalies(ArrayList<AnomalyRecord> theRecords) {
        myAnomalyDatabase.saveRecord(theRecords);
    }

    /**
     * Helper method to refresh the MonitorDashboard
     */
    private void refreshGUI() {

        /* Insert logic here */

    }
}
