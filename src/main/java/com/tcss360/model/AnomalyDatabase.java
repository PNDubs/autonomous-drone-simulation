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
        initializeDatabase();
    }

    /**
     * Stores the record to the database
     * @param theRecords the anomaly record to store
     */
    public void saveRecord(ArrayList<AnomalyRecord> theRecords) {
        if (theRecords == null || theRecords.isEmpty()) {
            return;
        }

        final String sql = "INSERT INTO anomalies "
                + "(record_id, drone_id, timestamp, anomaly_type, anomaly_details) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = myConnection.prepareStatement(sql)) {
            for (AnomalyRecord record : theRecords) {
                pstmt.setString(1, record.getRecordID().toString());
                pstmt.setInt(2, record.getDroneID());
                pstmt.setString(3, record.getTimestamp().toString());
                pstmt.setString(4, record.getAnomalyType());
                pstmt.setString(5, record.getAnomalyDetails());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves anomaly records for a specifc drone from the database
     * @param theDroneID the drone for which anomaly records are desired
     * @return a list of anomalies for a specific drone
     */
    public ArrayList<AnomalyRecord> getAnomaliesForDrone(final int theDroneID) {
        ArrayList<AnomalyRecord> records = new ArrayList<>();

        final String sql = "SELECT * FROM anomalies WHERE drone_id = ?";

        try (PreparedStatement pstmt = myConnection.prepareStatement(sql)) {
            pstmt.setInt(1, theDroneID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AnomalyRecord record = new AnomalyRecord(
                            rs.getInt("drone_id"),
                            rs.getString("anomaly_type"),
                            rs.getString("anomaly_details")
                    );
                    records.add(record);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }
    /**
     * Retrieves all anomaly records of a specific type from the database
     * @param theType the type of anomaly for which records are desired
     * @return a list of anomalies of a specific type
     */
    public ArrayList<AnomalyRecord> getAnomaliesByType(final String theType) {
        ArrayList<AnomalyRecord> records = new ArrayList<>();

        final String sql = "SELECT * FROM anomalies WHERE anomaly_type = ?";

        try (PreparedStatement pstmt = myConnection.prepareStatement(sql)) {
            pstmt.setString(1, theType);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AnomalyRecord record = new AnomalyRecord(
                            rs.getInt("drone_id"),
                            rs.getString("anomaly_type"),
                            rs.getString("anomaly_details")
                    );
                    records.add(record);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    /**
     * Retrieves all anomaly records over a period of time
     * @param theStart the start time
     * @param theEnd the end time
     * @return a list of anomaly records present over a period of time
     */
    public ArrayList<AnomalyRecord> getAnomaliesBetween(final LocalDateTime theStart,
                                                        final LocalDateTime theEnd) {
        ArrayList<AnomalyRecord> records = new ArrayList<>();

        final String sql = "SELECT * FROM anomalies WHERE timestamp >= ? AND timestamp <= ?";

        try (PreparedStatement pstmt = myConnection.prepareStatement(sql)) {
            pstmt.setString(1, theStart.toString());
            pstmt.setString(2, theEnd.toString());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AnomalyRecord record = new AnomalyRecord(
                            rs.getInt("drone_id"),
                            rs.getString("anomaly_type"),
                            rs.getString("anomaly_details")
                    );
                    records.add(record);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }
    /**
     * Exports all anomaly records in the database to a .CSV file
     * @param theFilePath where the .CSV file will be saved to
     */
    public void exportToCSV(final String theFilePath) {
        final String sql = "SELECT * FROM anomalies";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(theFilePath));
             Statement stmt = myConnection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            writer.write("record_id,drone_id,timestamp,anomaly_type,anomaly_details");
            writer.newLine();

            while (rs.next()) {
                writer.write(rs.getString("record_id") + ","
                        + rs.getInt("drone_id") + ","
                        + rs.getString("timestamp") + ","
                        + rs.getString("anomaly_type") + ","
                        + rs.getString("anomaly_details"));
                writer.newLine();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * closes the anomaly database
     */
    public void close() {
        if (myConnection != null) {
            try {
                myConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Initializes the anomaly database
     */
    private void initializeDatabase() {
        try {
            myConnection = DriverManager.getConnection(DB_URL);

            final String sql = "CREATE TABLE IF NOT EXISTS anomalies ("
                    + "record_id TEXT PRIMARY KEY, "
                    + "drone_id INTEGER NOT NULL, "
                    + "timestamp TEXT NOT NULL, "
                    + "anomaly_type TEXT NOT NULL, "
                    + "anomaly_details TEXT NOT NULL"
                    + ")";

            try (Statement stmt = myConnection.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
