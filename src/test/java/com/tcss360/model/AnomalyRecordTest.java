/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black, Matthew Park, Ibrahim Cartan
 */
package com.tcss360.model;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * 
 * @author Logan Black
 * @author Unknown
 * @version 02 June 2026
 */
public class AnomalyRecordTest {

    /**
     * 
     */
    @Test
    public void testConstructorStoresDroneIDCorrectly() {
        AnomalyRecord record = new AnomalyRecord(5, "Low Battery", "Battery dropped below 15%");
        assertEquals(5, record.getDroneID());
    }

    /**
     * 
     */
    @Test
    public void testConstructorStoresAnomalyTypeCorrectly() {
        AnomalyRecord record = new AnomalyRecord(5, "Low Battery", "Battery dropped below 15%");
        assertEquals("Low Battery", record.getAnomalyType());
    }

    /**
     * 
     */
    @Test
    public void testConstructorStoresAnomalyDetailsCorrectly() {
        AnomalyRecord record = new AnomalyRecord(5, "Low Battery", "Battery dropped below 15%");
        assertEquals("Battery dropped below 15%", record.getAnomalyDetails());
    }

    /**
     * 
     */
    @Test
    public void testRecordIDIsNotNull() {
        AnomalyRecord record = new AnomalyRecord(5,  "Low Battery", "Battery dropped below 15%");
        UUID recordID = record.getRecordID();
        assertNotNull(recordID);
    }

    /**
     * 
     */
    @Test
    public void testTimestampIsNotNull() {
        AnomalyRecord record = new AnomalyRecord(5,  "Low Battery", "Battery dropped below 15%");
        LocalDateTime timestap = record.getTimestap();
        assertNotNull(timestap);
    }

    /**
     * 
     */
    @Test
    public void testToStringReturnsNonEmptyString() {
        AnomalyRecord record = new AnomalyRecord(5,  "Low Battery", "Battery dropped below 15%");
        assertFalse(record.toString().isEmpty());
    }
}
