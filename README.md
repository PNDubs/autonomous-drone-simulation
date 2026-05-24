# Autonomous Drone Simulation

A Java-based Maven project that simulates a fleet of drones and random anomalous behavior employing object-oriented programming principles and collaborative development using YouTrack and GitHub.

Team Members:
* Logan Black
* Ibrahim Cartan
* Matthew Park

## GitHub Repo Link

https://github.com/PNDubs/autonomous-drone-simulation

## MVC Roles

Model: Drone, DroneSnapshot, TelemetryGenerator, AnomalyDetector, AnomalyRecord, AnomalyDatabase

View: MonitorDashboard

Controller: DroneMonitorApp

## Save Behavior

Anomalies are stored in a SQLite database 'anomalies.db'

anomalies.db is created in AnomalyDatabase.java initializeDatabase()

Query saved recods from the GUI using the Query section and making a selection

Export records to '.CSV' or '.PDF' using the file menu.

## Compile and Run

This project uses Maven and requires Java 21.

From the project root, compile the project:

```bash
mvn compile
```

Run the application from Main.java:

```bash
mvn exec:java -Dexec.mainClass="com.tcss360.controller.DroneMonitorApp"
```

## Issues
Merge Conflict: There were some issues when attempting to merge feature branches. The issue was solved by reviewing the incoming and current changes and accepting the neccesary changes.
