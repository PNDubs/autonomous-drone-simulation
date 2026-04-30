/*
 * Course Project - Autonomous Drone Simulation
 * TCSS 360 Spring 2026
 * Matthew Park
 */

package com.tcss360.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the DroneSnapshot class.
 * @author Matthew Park
 * @version 28 APR 2026
 */
public class DroneSnapshotTest {

    /** The drone snapshot instance used for testing */
    private DroneSnapshot mySnapshot;

    /** The drone instance used to create the snapshot */
    private Drone myDrone;

    /**
     * Test values
     */
    private static final int TEST_ID = 1;
    private static final double TEST_LONGITUDE = -999.9;
    private static final double TEST_LATITUDE = 99.9;
    private static final double TEST_ALTITUDE = 99.9;
    private static final int TEST_BATTERY = 99;
    private static final double TEST_HEADING = 99;
    private static final double TEST_SPEED = 5.0;

    /**
     * Sets up a fresh Drone and DroneSnapshot before each test.
     */
    @Before
    public void setUp() {
        myDrone = new Drone(TEST_ID, TEST_LONGITUDE, TEST_LATITUDE,
            TEST_ALTITUDE, TEST_BATTERY, TEST_HEADING, TEST_SPEED);
        mySnapshot = new DroneSnapshot(myDrone);
    }

    /**
     * Tests that the snapshot captures the drone ID correctly.
     */
    @Test
    public void testSnapshotID() {
        assertEquals(TEST_ID, mySnapshot.getID());
    }

    /**
     * Tests that the snapshot captures the longitude correctly.
     */
    @Test
    public void testSnapshotLongitude() {
        assertEquals(TEST_LONGITUDE, mySnapshot.getPreviousLongitude(), 0.0001);
    }

    /**
     * Tests that the snapshot captures the latitude correctly.
     */
    @Test
    public void testSnapshotLatitude() {
        assertEquals(TEST_LATITUDE, mySnapshot.getPreviousLatitude(), 0.0001);
    }

    /**
     * Tests that the snapshot captures the altitude correctly.
     */
    @Test
    public void testSnapshotAltitude() {
        assertEquals(TEST_ALTITUDE, mySnapshot.getPreviousAltitude(), 0.0001);
    }

    /**
     * Tests that the snapshot captures the battery level correctly.
     */
    @Test
    public void testSnapshotBatteryLevel() {
        assertEquals(TEST_BATTERY, mySnapshot.getPreviousBatteryLevel());
    }

    /**
     * Tests that the snapshot captures the heading correctly.
     */
    @Test
    public void testSnapshotHeading() {
        assertEquals(TEST_HEADING, mySnapshot.getPreviousHeading(), 0.0001);
    }

    /**
     * Tests that the snapshot captures the speed correctly.
     */
    @Test
    public void testSnapshotSpeed() {
        assertEquals(TEST_SPEED, mySnapshot.getPreviousSpeed(), 0.0001);
    }
}
