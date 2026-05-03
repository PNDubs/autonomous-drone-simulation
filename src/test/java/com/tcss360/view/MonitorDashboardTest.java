package com.tcss360.view;

import static org.junit.Assert.*;

import com.tcss360.model.AnomalyRecord;
import com.tcss360.model.Drone;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import org.junit.Test;

/**
 * Unit tests for MonitorDashboard.
 * These tests verify the class based on its current placeholder implementation.
 */
public class MonitorDashboardTest {

    /**
     * Confirms the constructor creates a MonitorDashboard object.
     */
    @Test
    public void testConstructorCreatesObject() {
        MonitorDashboard dashboard = new MonitorDashboard();
        assertNotNull(dashboard);
    }

    /**
     * Confirms display does not throw with an empty drone list.
     */
    @Test
    public void testDisplayDoesNotThrow() {
        MonitorDashboard dashboard = new MonitorDashboard();
        ArrayList<Drone> drones = new ArrayList<>();
        dashboard.display(drones);
    }

    /**
     * Confirms addAlert does not throw with a normal anomaly record.
     */
    @Test
    public void testAddAlertDoesNotThrow() {
        MonitorDashboard dashboard = new MonitorDashboard();
        AnomalyRecord record =
                new AnomalyRecord(1, "Low Battery", "Battery dropped below threshold");
        dashboard.addAlert(record);
    }

    /**
     * Confirms updateDroneTelemetry does not throw with an empty drone list.
     */
    @Test
    public void testUpdateDroneTelemetryDoesNotThrow() throws Exception {
        MonitorDashboard dashboard = new MonitorDashboard();

        Method method = MonitorDashboard.class.getDeclaredMethod(
                "updateDroneTelemetry", ArrayList.class);
        method.setAccessible(true);

        ArrayList<Drone> drones = new ArrayList<>();
        method.invoke(dashboard, drones);
    }

    /**
     * Confirms paintDronePositions does not throw with an empty drone list.
     */
    @Test
    public void testPaintDronePositionsDoesNotThrow() throws Exception {
        MonitorDashboard dashboard = new MonitorDashboard();

        Method method = MonitorDashboard.class.getDeclaredMethod(
                "paintDronePositions", ArrayList.class);
        method.setAccessible(true);

        ArrayList<Drone> drones = new ArrayList<>();
        method.invoke(dashboard, drones);
    }

    /**
     * Confirms showQueryScreen does not throw in its current placeholder form.
     */
    @Test
    public void testShowQueryScreenDoesNotThrow() throws Exception {
        MonitorDashboard dashboard = new MonitorDashboard();

        Method method = MonitorDashboard.class.getDeclaredMethod("showQueryScreen");
        method.setAccessible(true);
        method.invoke(dashboard);
    }

    /**
     * Confirms exportAnomalyLogToCSV does not throw with a normal file path.
     */
    @Test
    public void testExportAnomalyLogToCSVDoesNotThrow() throws Exception {
        MonitorDashboard dashboard = new MonitorDashboard();

        Method method = MonitorDashboard.class.getDeclaredMethod(
                "exportAnomalyLogToCSV", String.class);
        method.setAccessible(true);
        method.invoke(dashboard, "alerts.csv");
    }

    /**
     * Confirms buildMenuBar currently returns null.
     */
    @Test
    public void testBuildMenuBarReturnsNullForNow() throws Exception {
        MonitorDashboard dashboard = new MonitorDashboard();

        Method method = MonitorDashboard.class.getDeclaredMethod("buildMenuBar");
        method.setAccessible(true);

        JMenuBar menuBar = (JMenuBar) method.invoke(dashboard);
        assertNull(menuBar);
    }

    /**
     * Confirms handleExit does not throw in its current placeholder form.
     */
    @Test
    public void testHandleExitDoesNotThrow() throws Exception {
        MonitorDashboard dashboard = new MonitorDashboard();

        Method method = MonitorDashboard.class.getDeclaredMethod("handleExit");
        method.setAccessible(true);
        method.invoke(dashboard);
    }
}