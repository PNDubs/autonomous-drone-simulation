/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black, Matthew Park, Ibrahim Cartan
 */

package com.tcss360.model;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the TelemetryGenerator class.
 * @author Matthew Park
 * @author Logan Black
 * @version 02 June 2026
 */
public class TelemetryGeneratorTest {

    /** The telemetry generator used for testing */
    private TelemetryGenerator myGenerator;

    /** The list of drones used for testing */
    private ArrayList<Drone> myDrones;

    /**
     * Sets up a fresh TelemetryGenerator and drone list before each test.
     */
    @Before
    public void setUp() {
        myGenerator = new TelemetryGenerator();

        myDrones = new ArrayList<>();
        myDrones.add(new Drone(1, 50.0, 25.0, 100.0, 100, 0.0, 2.0));
        myDrones.add(new Drone(2, 50.0, 50.0, 100.0, 100, 0.0, 2.0));
        myDrones.add(new Drone(3, 50.0, 75.0, 100.0, 100, 0.0, 2.0));
    }

    /**
     * Tests that generateTelemetry returns a non null list.
     */
    @Test
    public void testGenerateTelemetryReturnsNonNullList() {
        ArrayList<DroneSnapshot> snapshots = myGenerator.generateTelemetry(myDrones);
        assertNotNull(snapshots);
    }

    /**
     * Tests that generateTelemetry returns an ArrayList.
     */
    @Test
    public void testGenerateTelemetryReturnsArrayList() {
        ArrayList<DroneSnapshot> snapshots = myGenerator.generateTelemetry(myDrones);
        assertTrue(snapshots instanceof ArrayList);
    }

    /**
     * Tests that generateTelemetry returns a snapshot for each drone.
     */
    @Test
    public void testGenerateTelemetryReturnsSameSizeAsDrones() {
        ArrayList<DroneSnapshot> snapshots = myGenerator.generateTelemetry(myDrones);
        assertEquals(myDrones.size(), snapshots.size());
    }
}
