package com.tcss360.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class TelemetryGeneratorTest {

    private TelemetryGenerator myGenerator;
    private ArrayList<Drone> myDrones;

    @Before
    public void setup() {
        myGenerator = new TelemetryGenerator();

        myDrones = new ArrayList<>();
        myDrones.add(new Drone(1, 50.0, 25.0, 100.0, 100, 0.0, 2.0));
        myDrones.add(new Drone(2, 50.0, 50.0, 100.0, 100, 0.0, 2.0));
        myDrones.add(new Drone(3, 50.0, 75.0, 100.0, 100, 0.0, 2.0));
    }

    @Test
    public void testGenerateTelemetryReturnsNonNullList() {
        ArrayList<DroneSnapshot> snapshots = myGenerator.generateTelemetry(myDrones);
        assertNotNull(snapshots);
    }

    @Test
    public void testGenerateTelemetryReturnsArrayList() {
        ArrayList<DroneSnapshot> snapshots = myGenerator.generateTelemetry(myDrones);
        assertTrue(snapshots instanceof ArrayList);
    }

    @Test
    public void testGenerateTelemetryReturnsSnapshotPerDrone() {
        ArrayList<DroneSnapshot> snapshots = myGenerator.generateTelemetry(myDrones);
        assertEquals(myDrones.size(), snapshots.size());
    }
}