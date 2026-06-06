/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black, Matthew Park, Ibrahim Cartan
 */
package com.tcss360.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Unit tests for AnomalyDetector.
 * These tests check the class based on its current implementation.
 * @author Logan Black
 * @author Matthew Park
 * @version 02 June 2026
 */
public class AnomalyDetectorTest {

    /**
     * Confirms the constructor stores all four threshold values correctly.
     * @throws Exception
     */
    @Test
    public void testConstructorStoresThresholdsCorrectly() throws Exception {
        AnomalyDetector detector = new AnomalyDetector(15.0, 20.0, 50.0, 30.0);

        Field lowBatteryField = AnomalyDetector.class.getDeclaredField("myLowBatteryThreshold");
        Field altitudeField = AnomalyDetector.class.getDeclaredField("myAltitudeThreshold");
        Field gpsField = AnomalyDetector.class.getDeclaredField("myGPSJumpThreshold");
        Field headingField = AnomalyDetector.class.getDeclaredField("myHeadingThreshold");

        lowBatteryField.setAccessible(true);
        altitudeField.setAccessible(true);
        gpsField.setAccessible(true);
        headingField.setAccessible(true);

        assertEquals(15.0, lowBatteryField.getDouble(detector), 0.001);
        assertEquals(20.0, altitudeField.getDouble(detector), 0.001);
        assertEquals(50.0, gpsField.getDouble(detector), 0.001);
        assertEquals(30.0, headingField.getDouble(detector), 0.001);
    }

    /**
     *Confirms detectAnomalies returns a  non-null list.
     */
    @Test
    public void testDetectAnomaliesReturnsnonNullList() {
        AnomalyDetector detector = new AnomalyDetector(15.0, 20.0, 50.0, 30.0);

        ArrayList<Drone> drones = new ArrayList<>();
        ArrayList<DroneSnapshot> snapshots = new ArrayList<>();

        ArrayList<AnomalyRecord> records = detector.detectAnomalies(drones, snapshots);

        assertNotNull(records);
    }

    /**
     * Confirms detectAnomalies currently returns an empty list.
     */
    @Test
    public void testDetectAnomaliesReturnsnonEmptyListForNow() {
        AnomalyDetector detector = new AnomalyDetector(15.0, 20.0, 50.0, 30.0);

        ArrayList<Drone> drones = new ArrayList<>();
        ArrayList<DroneSnapshot> snapshots = new ArrayList<>();

        ArrayList<AnomalyRecord> records = detector.detectAnomalies(drones, snapshots);

        assertEquals(0, records.size());
    }

    /**
     * Confirms the current low battery helper method returns false.
     * @throws Exception
     */
    @Test
    public void testCheckAltitudeReturnsFalseForNow() throws Exception {
        AnomalyDetector detector = new AnomalyDetector(15.0, 20.0, 50.0, 30.0);

        Method method = AnomalyDetector.class.getDeclaredMethod("checkAltitude", Drone.class, DroneSnapshot.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(detector, null, null);
        assertFalse(result);
    }

    /**
     * Confirms the current GPS spoofing helper method return false.
     * @throws Exception
     */
    @Test
    public void testCheckGPSSpoofingReturnsFalseForNow() throws Exception {
        AnomalyDetector detector = new AnomalyDetector(15.0, 20.0, 50.0, 30.0);

        Method method = AnomalyDetector.class.getDeclaredMethod("checkGPSSpoofing", Drone.class, DroneSnapshot.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(detector, null, null);

        assertFalse(result);
    }

    /**
     * Confirms the current unsafe movement helper method returns false
     * @throws Exception
     */
    @Test
    public void testCheckUnsafeMovementReturnsFalseForNow() throws Exception {
        AnomalyDetector detector = new AnomalyDetector(15.0, 20.0, 50.0, 30.0);

        Method method = AnomalyDetector.class.getDeclaredMethod("checkUnsafeMovement", Drone.class, DroneSnapshot.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(detector, null, null);

        assertFalse(result);
    }

    /**
     * Confirms the private createAnomalyRecord method creates a valid record.
     * @throws Exception
     */
    @Test
    public void testCreateAnomalyRecordReturnsValidRecord() throws Exception {
        AnomalyDetector detector = new AnomalyDetector(15.0, 20.0, 50.0, 30.0);

        Method method = AnomalyDetector.class.getDeclaredMethod(
                "createAnomalyRecord",
                int.class,
                String.class,
                String.class
        );
        method.setAccessible(true);

        AnomalyRecord record = (AnomalyRecord) method.invoke(
                detector,
                7,
                "Low Battery",
                "Battery dropped below threshold"
        );

        assertNotNull(record);
        assertEquals(7, record.getDroneID());
        assertEquals("Low Battery", record.getAnomalyType());
        assertEquals("Battery dropped below threshold", record.getAnomalyDetails());
    }

}
