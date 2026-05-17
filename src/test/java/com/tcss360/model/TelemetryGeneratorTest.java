/*
 * Course Project
 * TCSS 360 Spring 2026
 * Matthew Park
 */

package com.tcss360.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the TelemetryGenerator class.
 * @author Matthew Park
 * @version 16 May 2026
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
