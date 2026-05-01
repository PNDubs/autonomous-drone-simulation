/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.controller;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
 * @version 30 APR 2026
 */
public class DroneMonitorApp {

    /** The value representing a fully charged battery */
    private static final int FULL_BATTERY_LEVEL = 100;

    /** The low battery level constituting an anomaly */
    private static final int LOW_BATTERY_THRESHOLD = 15;

    /** Default heading of 0 degrees for initialization */
    private static final double DEFAULT_HEADING = 0.0;

    /** Default speed of 3.0 meters / second for initialization */
    private static final double DEFAULT_SPEED = 3.0;

    /** Default altitude of 100 meters for initialization */
    private static final double DEFAULT_ALTITUDE = 100.0;

    /** Default longitude of 0 meters for initialization */
    private static final double DEFAULT_LONGITUDE = 50.0;

    /** Default latitude of 0 meters for initialization */
    private static final double DEFAULT_LATITUDE = 25.0;

    /** 
     * The change in longitude, latitude, or altitude in meters over 
     * 0.25 seconds constituting an anomlay 
     */
    private static final double GPS_JUMP_THRESHOLD = 0.25;

    /** The change in degrees over 0.25 seconds constituting an anomaly */
    private static final double HEADING_THRESHOLD = 45.0;

    /** System time period representative of 240 Hz */
    private static final long PERIOD = (long) (1_000_000_000.0 / 240.0);

    /** The number of drones to simulate */
    private static final int NUM_DRONES = 3;

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
    private ScheduledExecutorService myExecutor;

    public DroneMonitorApp() {
        myDrones = initializeDrones();
        myDroneSnapshots = new ArrayList<>();
        myAnomalyDetector = new AnomalyDetector(LOW_BATTERY_THRESHOLD, 
            HEADING_THRESHOLD, GPS_JUMP_THRESHOLD);
        myMonitorDashboard = initializeMonitorDashboard();
        myAnomalyDatabase = new AnomalyDatabase();
        myExecutor = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * The method used to start the drone monitor app controller
     */
    public void start() {

        /* Insert logic here */

    }

    /**
     * Helper method used to initialize 3 drones
     * @return a list of drones
     */
    private ArrayList<Drone> initializeDrones() {

        ArrayList<Drone> drones = new ArrayList<>();

        for (int i = 1; i <= NUM_DRONES; i++) {
            drones.add(new Drone(i, DEFAULT_LONGITUDE, 
                (DEFAULT_LATITUDE + ((i - 1) * DEFAULT_LATITUDE)), 
                DEFAULT_ALTITUDE, FULL_BATTERY_LEVEL, DEFAULT_HEADING, 
                DEFAULT_SPEED));
        }

        return drones;
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
        myDroneSnapshots = TelemetryGenerator.generateTelemetry(myDrones);
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
