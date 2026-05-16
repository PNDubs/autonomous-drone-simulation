/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * The AnomalyDatabase class acts as the bridge between the controller and
 * the anomaly database.
 * @author Logan Black
 * @version 28 APR 2026
 */
public class AnomalyDatabase {

    /** The database url */
    private static final String DB_URL = "jdbc:sqlite:anomalies.db";

    /** The database connection */
    private Connection myConnection;

    /**
     * Constructor
     */
    public AnomalyDatabase() {

        try {
            myConnection = DriverManager.getConnection(DB_URL);
            initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Stores the record to the database
     * @param theRecords the anomaly record to store
     */
    public void saveRecord(ArrayList<AnomalyRecord> theRecords) {

        String sql = "INSERT INTO anomalies (record_id, drone_id, timestamp, anomaly_type, anomaly_details) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = myConnection.prepareStatement(sql);

            for (AnomalyRecord record : theRecords) {
                stmt.setString(1, record.getRecordID().toString());
                stmt.setInt(2, record.getDroneID());
                stmt.setString(3, record.getTimestap().toString());
                stmt.setString(4, record.getAnomalyType());
                stmt.setString(5, record.getAnomalyDetails());
                stmt.executeUpdate();
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieves anomaly records for a specifc drone from the database
     * @param theDroneID the drone for which anomaly records are desired
     * @return a list of anomalies for a specific drone
     */
    public ArrayList<AnomalyRecord> getAnomaliesForDrone(int theDroneID) {

        ArrayList<AnomalyRecord> theRecords = new ArrayList<>();
        String sql = "SELECT * FROM anomalies WHERE drone_id = ?";

        try {
            PreparedStatement stmt = myConnection.prepareStatement(sql);
            stmt.setInt(1, theDroneID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int droneID = rs.getInt("drone_id");
                String anomalyType = rs.getString("anomaly_type");
                String anomalyDetails = rs.getString("anomaly_details");
                theRecords.add(new AnomalyRecord(droneID, anomalyType, anomalyDetails));
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theRecords;
    }
    /**
     * Retrieves all anomaly records of a specific type from the database
     * @param theType the type of anomaly for which records are desired
     * @return a list of anomalies of a specific type
     */
    public ArrayList<AnomalyRecord> getAnomaliesByType(String theType) {

        ArrayList<AnomalyRecord> theRecords = new ArrayList<>();
        String sql = "SELECT * FROM anomalies WHERE anomaly_type = ?";

        try {
            PreparedStatement stmt = myConnection.prepareStatement(sql);
            stmt.setString(1, theType);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int droneID = rs.getInt("drone_id");
                String anomalyType = rs.getString("anomaly_type");
                String anomalyDetails = rs.getString("anomaly_details");
                theRecords.add(new AnomalyRecord(droneID, anomalyType, anomalyDetails));
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theRecords;
    }

    /**
     * Retrieves all anomaly records over a period of time
     * @param theStart the start time
     * @param theEnd the end time
     * @return a list of anomaly records present over a period of time
     */
    public ArrayList<AnomalyRecord> getAnomaliesBetween(LocalDateTime theStart,
        LocalDateTime theEnd) {

            ArrayList<AnomalyRecord> theRecords = new ArrayList<>();
            String sql = "SELECT * FROM anomalies WHERE timestamp BETWEEN ? AND ?";

            try {
                PreparedStatement stmt = myConnection.prepareStatement(sql);
                stmt.setString(1, theStart.toString());
                stmt.setString(2, theEnd.toString());
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int droneID = rs.getInt("drone_id");
                    String anomalyType = rs.getString("anomaly_type");
                    String anomalyDetails = rs.getString("anomaly_details");
                    theRecords.add(new AnomalyRecord(droneID, anomalyType, anomalyDetails));
                }

                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return theRecords;
    }
    /**
     * Exports all anomaly records in the database to a .CSV file
     * @param theFilePath where the .CSV file will be saved to
     */
    public void exportToCSV(String theFilePath) {

        String sql = "SELECT * FROM anomalies";

        try {
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            FileWriter writer = new FileWriter(theFilePath);

            writer.write("RecordID,DroneID,Timestamp,AnomalyType,AnomalyDetails\n");

            while (rs.next()) {
                String line = rs.getString("record_id") + ","
                    + rs.getInt("drone_id") + ","
                    + rs.getString("timestamp") + ","
                    + rs.getString("anomaly_type") + ","
                    + rs.getString("anomaly_details") + "\n";
                writer.write(line);
            }

            writer.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * closes the anomaly database
     */
    public void close() {

        try {
            if (myConnection != null) {
                myConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Initializes the anomaly database
     */
    private void initializeDatabase() {

        String sql = "CREATE TABLE IF NOT EXISTS anomalies ("
            + "record_id TEXT PRIMARY KEY, "
            + "drone_id INTEGER NOT NULL, "
            + "timestamp TEXT NOT NULL, "
            + "anomaly_type TEXT NOT NULL, "
            + "anomaly_details TEXT NOT NULL)";

        try {
            Statement stmt = myConnection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
