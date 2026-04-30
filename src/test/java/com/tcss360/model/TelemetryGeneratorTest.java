package com.tcss360.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class TelemetryGeneratorTest {

    @Test
    public void testGenerateTelemetryReturnsNonNullList() {
        ArrayList<DroneSnapshot> snapshots = TelemetryGenerator.generateTelemetry();
        assertNotNull(snapshots);
    }

    @Test
    public void testGenerateTelemetryReturnsArrayList() {
        ArrayList<DroneSnapshot> snapshots = TelemetryGenerator.generateTelemetry();
        assertTrue(snapshots instanceof ArrayList);
    }

    @Test
    public void testGenerateTelemetryReturnsEmptyArrayListForNow() {
        ArrayList<DroneSnapshot> snapshots = TelemetryGenerator.generateTelemetry();
        assertEquals(0,snapshots.size());
    }
}