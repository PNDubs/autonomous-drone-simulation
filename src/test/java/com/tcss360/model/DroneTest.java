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
 * Unit tests for the Drone class.
 * @author Matthew Park
 * @author Logan Black
 * @version 15 May 2026
 */
public class DroneTest {

    /** 
     * The drone instance used for testing 
     */
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
     * Sets up a fresh Drone before each test.
     */
    @Before
    public void setUp() {
        myDrone = new Drone(TEST_ID, TEST_LONGITUDE, TEST_LATITUDE,
            TEST_ALTITUDE, TEST_BATTERY, TEST_HEADING, TEST_SPEED);
    }

    /**
     * Tests that the constructor sets the ID correctly.
     */
    @Test
    public void testConstructorID() {
        assertEquals(TEST_ID, myDrone.getID());
    }

    /**
     * Tests that the constructor sets the longitude correctly.
     */
    @Test
    public void testConstructorLongitude() {
        assertEquals(TEST_LONGITUDE, myDrone.getLongitude(), 0.0001);
    }

    /**
     * Tests that the constructor sets the latitude correctly.
     */
    @Test
    public void testConstructorLatitude() {
        assertEquals(TEST_LATITUDE, myDrone.getLatitude(), 0.0001);
    }

    /**
     * Tests that the constructor sets the altitude correctly.
     */
    @Test
    public void testConstructorAltitude() {
        assertEquals(TEST_ALTITUDE, myDrone.getAltitude(), 0.0001);
    }

    /**
     * Tests that the constructor sets the battery level correctly.
     */
    @Test
    public void testConstructorBatteryLevel() {
        assertEquals(TEST_BATTERY, myDrone.getBatteryLevel(), 0.0001);
    }

    /**
     * Tests that the constructor sets the heading correctly.
     */
    @Test
    public void testConstructorHeading() {
        assertEquals(TEST_HEADING, myDrone.getHeading(), 0.0001);
    }

    /**
     * Tests that the constructor sets the speed correctly.
     */
    @Test
    public void testConstructorSpeed() {
        assertEquals(TEST_SPEED, myDrone.getSpeed(), 0.0001);
    }

    /**
     * Tests that setLongitude updates the longitude correctly.
     */
    @Test
    public void testSetLongitude() {
        myDrone.setLongitude(-118.2437);
        assertEquals(-118.2437, myDrone.getLongitude(), 0.0001);
    }

    /**
     * Tests that setLatitude updates the latitude correctly.
     */
    @Test
    public void testSetLatitude() {
        myDrone.setLatitude(34.0522);
        assertEquals(34.0522, myDrone.getLatitude(), 0.0001);
    }

    /**
     * Tests that setAltitude updates the altitude correctly.
     */
    @Test
    public void testSetAltitude() {
        myDrone.setAltitude(200.0);
        assertEquals(200.0, myDrone.getAltitude(), 0.0001);
    }

    /**
     * Tests that setBatteryLevel updates the battery level correctly.
     */
    @Test
    public void testSetBatteryLevel() {
        myDrone.setBatteryLevel(50);
        assertEquals(50, myDrone.getBatteryLevel(), 0.0001);
    }

    /**
     * Tests that setHeading updates the heading correctly.
     */
    @Test
    public void testSetHeading() {
        myDrone.setHeading(90.0);
        assertEquals(90.0, myDrone.getHeading(), 0.0001);
    }

    /**
     * Tests that setSpeed updates the speed correctly.
     */
    @Test
    public void testSetSpeed() {
        myDrone.setSpeed(25.0);
        assertEquals(25.0, myDrone.getSpeed(), 0.0001);
    }
}
