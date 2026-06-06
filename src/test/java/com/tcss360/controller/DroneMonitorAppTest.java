/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black, Matthew Park, Ibrahim Cartan
 */
package com.tcss360.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import com.tcss360.model.AnomalyDatabase;
import com.tcss360.model.AnomalyDetector;
import com.tcss360.model.AnomalyRecord;
import com.tcss360.model.Drone;
import com.tcss360.view.MonitorDashboard;

/**
 * Unit tests for DroneMonitorApp.
 * These tests verify the class based on its current implementation.
 * @author Logan Black
 * @author Matthew Park
 * @version 02 June 2026
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
     * @throws Exception
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
     * Confirms the constructor initializes the drone fleet with 3 drones.
     * @throws Exception
     */
    @Test
    public void testConstructorInitializesDrones() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Field dronesField = DroneMonitorApp.class.getDeclaredField("myDrones");
        dronesField.setAccessible(true);

        ArrayList<?> drones = (ArrayList<?>) dronesField.get(app);

        assertNotNull(drones);
        assertEquals(3, drones.size());
    }

    /**
     * Confirms the constructor creates an anomaly detector object.
     * @throws Exception
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
     * @throws Exception
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
     * Confirms the constructor initializes the monitor dashboard.
     * @throws Exception
     */
    @Test
    public void testConstructorInitializesDashboard() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Field dashboardField = DroneMonitorApp.class.getDeclaredField("myMonitorDashboard");
        dashboardField.setAccessible(true);

        MonitorDashboard dashboard = (MonitorDashboard) dashboardField.get(app);

        assertNotNull(dashboard);
    }

    /**
     * Confirms the constructor initializes the scheduled executor service.
     * @throws Exception
     */
    @Test
    public void testConstructorInitializesTimer() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Field timerField = DroneMonitorApp.class.getDeclaredField("myExecutor");
        timerField.setAccessible(true);

        Object executor = timerField.get(app);

        assertNotNull(executor);
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
     * Confirms initializeDrones() returns a list of 3 drones.
     * @throws Exception
     */
    @Test
    public void testInitializeDronesReturnsList() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Method method = DroneMonitorApp.class.getDeclaredMethod("initializeDrones");
        method.setAccessible(true);

        ArrayList<?> drones = (ArrayList<?>) method.invoke(app);

        assertNotNull(drones);
        assertEquals(3, drones.size());
    }

    /**
     * Confirms updateTelemetry() sets the snapshot list to match the drone count.
     * @throws Exception
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
        assertEquals(3, snapshots.size());
    }

    /**
     * Confirms checkForAnomalies() does not throw after telemetry is updated.
     * @throws Exception
     */
    @Test
    public void testCheckForAnomaliesDoesNotThrow() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Method updateMethod = DroneMonitorApp.class.getDeclaredMethod("updateTelemetry");
        updateMethod.setAccessible(true);
        updateMethod.invoke(app);

        Method checkMethod = DroneMonitorApp.class.getDeclaredMethod("checkForAnomalies");
        checkMethod.setAccessible(true);
        checkMethod.invoke(app);
    }

    /**
     * Confirms saveAnomalies() does not throw with an empty record list.
     * @throws Exception
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
     * @throws Exception
     */
    @Test
    public void testRefreshGUIDoesNotThrow() throws Exception {
        DroneMonitorApp app = new DroneMonitorApp();

        Method method = DroneMonitorApp.class.getDeclaredMethod("refreshGUI");
        method.setAccessible(true);
        method.invoke(app);
    }
}
