/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black, Matthew Park, Ibrahim Cartan
 */
package com.tcss360.view;

import static org.junit.Assert.*;

import com.tcss360.model.AnomalyDatabase;
import com.tcss360.model.AnomalyRecord;
import com.tcss360.model.Drone;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import org.junit.Test;

/**
 * Unit tests for MonitorDashboard.
 * These tests verify the class based on its current placeholder implementation.
 * @author Logan Black
 * @author Matthew Park
 * @version 02 June 2026
 */
public class MonitorDashboardTest {

    /**
     * Confirms the constructor creates a MonitorDashboard object.
     */
    @Test
    public void testConstructorCreatesObject() {
        MonitorDashboard dashboard = new MonitorDashboard(new AnomalyDatabase());
        assertNotNull(dashboard);
    }

    /**
     * Confirms display does not throw with an empty drone list.
     */
    @Test
    public void testDisplayDoesNotThrow() {
        MonitorDashboard dashboard = new MonitorDashboard(new AnomalyDatabase());
        ArrayList<Drone> drones = new ArrayList<>();
        dashboard.display(drones);
    }

    /**
     * Confirms addAlert does not throw with a normal anomaly record.
     */
    @Test
    public void testAddAlertDoesNotThrow() {
        MonitorDashboard dashboard = new MonitorDashboard(new AnomalyDatabase());
        AnomalyRecord record =
                new AnomalyRecord(1, "Low Battery", "Battery dropped below threshold");
        dashboard.addAlert(record);
    }


    /**
     * Confirms showQueryScreen does not throw in its current placeholder form.
     * @throws Exception
     */
    @Test
    public void testShowQueryScreenDoesNotThrow() throws Exception {
        MonitorDashboard dashboard = new MonitorDashboard(new AnomalyDatabase());

        Method method = MonitorDashboard.class.getDeclaredMethod("showQueryScreen");
        method.setAccessible(true);
        method.invoke(dashboard);
    }

    /**
     * Confirms exportAnomalyLogToPDF does not throw with a normal file path.
     * @throws Exception
     */
    @Test
    public void testExportAnomalyLogToPDFDoesNotThrow() throws Exception {
        MonitorDashboard dashboard = new MonitorDashboard(new AnomalyDatabase());

        Method method = MonitorDashboard.class.getDeclaredMethod("exportAnomalyLogToPDF", String.class);        method.setAccessible(true);
        method.setAccessible(true);
        method.invoke(dashboard, "test.pdf");

    }

    /**
     * Confirms buildMenuBar currently returns null.
     * @throws Exception
     */
    @Test
    public void testBuildMenuBarReturnsMenuBar() throws Exception {
        MonitorDashboard dashboard = new MonitorDashboard(new AnomalyDatabase());

        Method method = MonitorDashboard.class.getDeclaredMethod("buildMenuBar");
        method.setAccessible(true);

        JMenuBar menuBar = (JMenuBar) method.invoke(dashboard);
        assertNotNull(menuBar);
    }

}
