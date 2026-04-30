package com.tcss360.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.Test;

public class AnomalyRecordTest {

    @Test
    public void testConstructorStoresDroneIDCorrectly() {
        AnomalyRecord record = new AnomalyRecord(5, "Low Battery", "Battery dropped below 15%");
        assertEquals(5, record.getDroneID());
    }

    @Test
    public void testConstructorStoresAnomalyTypeCorrectly() {
        AnomalyRecord record = new AnomalyRecord(5, "Low Battery", "Battery dropped below 15%");
        assertEquals("Low Battery", record.getAnomalyType());
    }
    @Test
    public void testConstructorStoresAnomalyDetailsCorrectly() {
        AnomalyRecord record = new AnomalyRecord(5, "Low Battery", "Battery dropped below 15%");
        assertEquals("Battery dropped below 15%", record.getAnomalyDetails());
    }

    @Test
    public void testRecordIDIsNotNull() {
        AnomalyRecord record = new AnomalyRecord(5,  "Low Battery", "Battery dropped below 15%");
        UUID recordID = record.getRecordID();
        assertNotNull(recordID);
    }

    @Test
    public void testTimestampIsNotNull() {
        AnomalyRecord record = new AnomalyRecord(5,  "Low Battery", "Battery dropped below 15%");
        LocalDateTime timestap = record.getTimestap();
        assertNotNull(timestap);
    }

    @Test
    public void testToStringReturnsEmptyStringForNow() {
        AnomalyRecord record = new AnomalyRecord(5,  "Low Battery", "Battery dropped below 15%");
        assertEquals("", record.toString());
    }
}
