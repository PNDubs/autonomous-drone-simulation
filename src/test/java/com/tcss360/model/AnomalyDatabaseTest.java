package com.tcss360.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Unit tests for AnomalyDatabase
 * These tests verify the class based on its current placeholder implementation.
 */

public class AnomalyDatabaseTest {

    /**
     * Confirms the constructor creates an object successfully.
     */
    @Test
    public void testConstructorCreatedObject() {
        AnomalyDatabase database = new AnomalyDatabase();
        assertNotNull(database);

    }

    /**
     * Confirms getAnomaliesForDrone currently returns null.
     */

    @Test
    public void testAnomaliesForDroneReturnsNullForNow() {
        AnomalyDatabase database = new AnomalyDatabase();
        ArrayList<AnomalyRecord> result = database.getAnomaliesForDrone(5);
        assertNull(result);
    }

    /**
     * Confirms getAnomaliesByType currently returns null.
     */

    @Test
    public void testGetAnomaliesByTypeReturnsNullForNow() {
        AnomalyDatabase database = new AnomalyDatabase();
        ArrayList<AnomalyRecord> result = database.getAnomaliesByType("Low Battery");
        assertNull(result);
    }

    /**
     * Confirms getAnomaliesBetween currently returns null.
     */

    @Test
    public void testGetAnomaliesBetweenReturnsNullForNow() {
        AnomalyDatabase database = new AnomalyDatabase();
        ArrayList<AnomalyRecord> result = database.getAnomaliesBetween(LocalDateTime.now().minusHours(1), LocalDateTime.now());
        assertNull(result);
    }

    /**
     * Confirms exportToCSV does not throw an exception with a normal file path.
     */

    @Test
    public void testExportToCSVDoesNotThrow() {
        AnomalyDatabase database = new AnomalyDatabase();
        database.exportToCSV("test.csv");
    }

    /**
     * Confirms close does not throw an exception.
     */

    @Test
    public void testCloseDoesNotThrow() {
        AnomalyDatabase database = new AnomalyDatabase();
        database.close();
    }
}
