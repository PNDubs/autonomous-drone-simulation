package com.tcss360.controller;

import static org.junit.Assert.*;

import com.tcss360.model.AnomalyDatabase;
import com.tcss360.model.AnomalyDetector;
import com.tcss360.model.AnomalyRecord;
import com.tcss360.model.Drone;
import com.tcss360.view.MonitorDashboard;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Timer;
import org.junit.Test;

/**
 * Unit tests for DroneMonitorApp.
 * These tests verify the class based on its current implementation.
 */
public class DroneMonitorAppTest {

    /**
     * Confirms the constructor creates a DroneMonitorApp object.
     */
    @Test
    public void testConstructorCreatesObject() {
        DroneMonitorApp app = new DroneMonitorApp();
        assertNotNull(app);
    }

    /**
     * Confirms the constructor initializes the snapshot list.
     */
    @Test
    public void testConstructorInitializesSnapshotList() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Field snapshotsField = DroneMonitorApp.class.getDeclaredField("myDroneSnapshots");
        snapshotsField.setAccessible(true);

        ArrayList<?> snapshots = (ArrayList<?>) snapshotsField.get(app);

        assertNotNull(snapshots);
        assertEquals(0, snapshots.size());
    }

    /**
     * Confirms the constructor currently leaves drones as null
     * because initializeDrones() returns null for now.
     */
    @Test
    public void testConstructorLeavesDronesNullForNow() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Field dronesField = DroneMonitorApp.class.getDeclaredField("myDrones");
        dronesField.setAccessible(true);

        Object drones = dronesField.get(app);

        assertNull(drones);
    }

    /**
     * Confirms the constructor creates an anomaly detector object.
     */
    @Test
    public void testConstructorInitializesAnomalyDetector() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Field detectorField = DroneMonitorApp.class.getDeclaredField("myAnomalyDetector");
        detectorField.setAccessible(true);

        AnomalyDetector detector = (AnomalyDetector) detectorField.get(app);

        assertNotNull(detector);
    }

    /**
     * Confirms the constructor creates an anomaly database object.
     */
    @Test
    public void testConstructorInitializesAnomalyDatabase() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Field databaseField = DroneMonitorApp.class.getDeclaredField("myAnomalyDatabase");
        databaseField.setAccessible(true);

        AnomalyDatabase database = (AnomalyDatabase) databaseField.get(app);

        assertNotNull(database);
    }

    /**
     * Confirms the constructor currently leaves the dashboard as null
     * because initializeMonitorDashboard() returns null for now.
     */
    @Test
    public void testConstructorLeavesDashboardNullForNow() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Field dashboardField = DroneMonitorApp.class.getDeclaredField("myMonitorDashboard");
        dashboardField.setAccessible(true);

        MonitorDashboard dashboard = (MonitorDashboard) dashboardField.get(app);

        assertNull(dashboard);
    }

    /**
     * Confirms the constructor creates a timer object.
     */
    @Test
    public void testConstructorInitializesTimer() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Field timerField = DroneMonitorApp.class.getDeclaredField("myUpdateTimer");
        timerField.setAccessible(true);

        Timer timer = (Timer) timerField.get(app);

        assertNotNull(timer);
    }

    /**
     * Confirms start() does not throw an exception
     * in its current placeholder form.
     */
    @Test
    public void testStartDoesNotThrow() {
        DroneMonitorApp app = new DroneMonitorApp();
        app.start();
    }

    /**
     * Confirms initializeDrones() currently returns null.
     */
    @Test
    public void testInitializeDronesReturnsNullForNow() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Method method = DroneMonitorApp.class.getDeclaredMethod("initializeDrones");
        method.setAccessible(true);

        ArrayList<Drone> drones = (ArrayList<Drone>) method.invoke(app);

        assertNull(drones);
    }

    /**
     * Confirms initializeMonitorDashboard() currently returns null.
     */
    @Test
    public void testInitializeMonitorDashboardReturnsNullForNow() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Method method = DroneMonitorApp.class.getDeclaredMethod("initializeMonitorDashboard");
        method.setAccessible(true);

        MonitorDashboard dashboard = (MonitorDashboard) method.invoke(app);

        assertNull(dashboard);
    }

    /**
     * Confirms updateTelemetry() sets the snapshot list to a non-null value.
     * TelemetryGenerator currently returns an empty list, so the result should exist.
     */
    @Test
    public void testUpdateTelemetrySetsSnapshotList() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Method method = DroneMonitorApp.class.getDeclaredMethod("updateTelemetry");
        method.setAccessible(true);
        method.invoke(app);

        Field snapshotsField = DroneMonitorApp.class.getDeclaredField("myDroneSnapshots");
        snapshotsField.setAccessible(true);

        ArrayList<?> snapshots = (ArrayList<?>) snapshotsField.get(app);

        assertNotNull(snapshots);
        assertEquals(0, snapshots.size());
    }

    /**
     * Confirms checkForAnomalies() does not throw.
     * The current detector returns an empty list, so nothing should be saved.
     */
    @Test
    public void testCheckForAnomaliesDoesNotThrow() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Method method = DroneMonitorApp.class.getDeclaredMethod("checkForAnomalies");
        method.setAccessible(true);
        method.invoke(app);
    }

    /**
     * Confirms saveAnomalies() does not throw with an empty record list.
     */
    @Test
    public void testSaveAnomaliesDoesNotThrow() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Method method = DroneMonitorApp.class.getDeclaredMethod("saveAnomalies", ArrayList.class);
        method.setAccessible(true);

        ArrayList<AnomalyRecord> records = new ArrayList<>();
        method.invoke(app, records);
    }

    /**
     * Confirms refreshGUI() does not throw in its current placeholder form.
     */
    @Test
    public void testRefreshGUIDoesNotThrow() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Method method = DroneMonitorApp.class.getDeclaredMethod("refreshGUI");
        method.setAccessible(true);
        method.invoke(app);
    }
}