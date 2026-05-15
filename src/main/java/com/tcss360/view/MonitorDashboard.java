/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.tcss360.model.AnomalyRecord;
import com.tcss360.model.Drone;

/**
 * The MonitorDashboard class is the GUI for human-system interaction
 * @author Logan Black
 * @version 10 May 2026
 */
public class MonitorDashboard {

    /** Root panel for display inside myFrame */
    private final JPanel myRootPanel;

    /** The drone table */
    private JTable myDroneTable;

    /** The drone map panel */
    private final DroneMapPanel myMapPanel;

    /** The text area for the alert log */
    private final JTextArea myTextArea;

    /** The alert log area */
    private final JScrollPane myAlertLog;

    /** The alert log query area */
    private final JPanel myQueryPanel;

    /**
     * Constructor
     */
    public MonitorDashboard() {
        myMapPanel = buildMapPanel();
        myQueryPanel = buildQueryPanel();
        myTextArea = buildTextArea();
        myAlertLog = buildAlertLog();
        myRootPanel = buildRootPanel();
        buildFrame();
    }

    /**
     * 
     * @param theDrones the drone fleet
     */
    public void display(ArrayList<Drone> theDrones) {

        updateDroneTelemetry(theDrones);

    }

    /**
     * 
     * @param theRecord an anomaly record
     */
    public void addAlert(AnomalyRecord theRecord) {

        myTextArea.append(theRecord.toString() + "\n");

    }

    /**
     * Builds the map panel
     * @return a completed map panel
     */
    private DroneMapPanel buildMapPanel() {
        DroneMapPanel panel = new DroneMapPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Map"));
        panel.setMinimumSize(new Dimension(400, 300));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    /**
     * Builds the query panel
     * @return a completed query panel
     */
    private JPanel buildQueryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Query"));
        panel.setPreferredSize(new Dimension(0, 120));
        panel.setMinimumSize(new Dimension(400,120));
        panel.add(new JLabel("query", SwingConstants.CENTER), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Builds the alert text area
     * @return a completed alert text area
     */
    private JTextArea buildTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    /**
     * Builds the alert log
     * @return a completed alert log
     */
    private JScrollPane buildAlertLog() {
        JScrollPane alertScrollPane = new JScrollPane(myTextArea);
        alertScrollPane.setBorder(BorderFactory.createTitledBorder("Alerts"));
        alertScrollPane.setPreferredSize(new Dimension(220, 0));
       
        return alertScrollPane;
    }

    /**
     * Builds the root panel for display in the frame
     * @return a completed root panel
     */
    private JPanel buildRootPanel() {
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.add(myMapPanel, BorderLayout.CENTER);
        rootPanel.add(myQueryPanel, BorderLayout.SOUTH);
        rootPanel.add(myAlertLog, BorderLayout.EAST);
        return rootPanel;
    }

    /**
     * Builds the frame for gui display
     */
    private void buildFrame() {
        JFrame frame = new JFrame("Autonomous Drone Simulator");

        frame.setJMenuBar(buildMenuBar());
        frame.setContentPane(myRootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800,400));
        frame.setSize(1000,650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * 
     * @return
     */
    private JMenuBar buildMenuBar() {
        return new JMenuBar();
    }

    /**
     * 
     * @param theDrones the drone fleet
     */
    private void updateDroneTelemetry(ArrayList<Drone> theDrones) {
        myMapPanel.setDrones(theDrones);

        /* Insert Logic Here */

    }

    /**
     *
     */
    private void showQueryScreen() {

        String[] options = {"By Drone ID", "By Anomaly Type", "By Time Range"};

        int choice = JOptionPane.showOptionDialog(
            null,
            "Select a query type:",
            "Query Anomaly Database",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choice == 0) {
            String input = JOptionPane.showInputDialog("Enter Drone ID:");
            if (input != null) {
                JOptionPane.showMessageDialog(null, "Querying anomalies for Drone ID: " + input);
            }
        } else if (choice == 1) {
            String input = JOptionPane.showInputDialog("Enter Anomaly Type (e.g. LOW_BATTERY):");
            if (input != null) {
                JOptionPane.showMessageDialog(null, "Querying anomalies of type: " + input);
            }
        } else if (choice == 2) {
            String start = JOptionPane.showInputDialog("Enter start time (yyyy-MM-ddTHH:mm:ss):");
            String end = JOptionPane.showInputDialog("Enter end time (yyyy-MM-ddTHH:mm:ss):");
            if (start != null && end != null) {
                JOptionPane.showMessageDialog(null, "Querying anomalies between: " + start + " and " + end);
            }
        }

    }

    /**
     * 
     * @param theFilePath the file save path
     */
    private void exportAnomalyLogToCSV(String theFilePath) {

        String logText = myTextArea.getText();

        try {
            java.io.FileWriter writer = new java.io.FileWriter(theFilePath);
            writer.write("Timestamp,DroneID,AnomalyType,AnomalyDetails\n");
            writer.write(logText);
            writer.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    private void handleExit() {

        int choice = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to exit?",
            "Exit",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

    /**
     * Builds drones for GUI testing.
     * @return list of drones
     */
    private static ArrayList<Drone> buildTestDrones() {
        ArrayList<Drone> drones = new ArrayList<>();

        final int fullBatteryLevel = 100;
        final double defaultHeading = 0.0;
        final double defaultSpeed = 2.0;
        final double defaultAltitude = 100.0;
        final double defaultLongitude = 50.0;
        final double defaultLatitude = 25.0;
        final int numDrones = 3;

        for (int i = 1; i <= numDrones; i++) {
            drones.add(new Drone(
                i,
                defaultLongitude,
                defaultLatitude + ((i - 1) * defaultLatitude),
                defaultAltitude,
                fullBatteryLevel,
                defaultHeading,
                defaultSpeed
            ));
        }

        return drones;
    }


    /**
     * Main method for testing only
     * @param args unused.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MonitorDashboard dashboard = new MonitorDashboard();
            dashboard.updateDroneTelemetry(buildTestDrones());
        });
    }

    /**
     * An innter class to act as a custom JPanel for drawing drone positions.
     */
    private final class DroneMapPanel extends JPanel {

        /** The radius of a drone object on the map in px */
        private static final int DRONE_RADIUS = 2;

        /** The conversion rate from 1 meter to # px */
        private static final double METERS_TO_PIXELS = 4.0;

        /** The drones to paint */
        private ArrayList<Drone> myDrones = new ArrayList<>();

        /**
         * Sets the drone positions and repaints them
         * @param theDrones the list of drones
         */
        private void setDrones(ArrayList<Drone> theDrones) {
            myDrones = theDrones;
            repaint();
        }

        /**
         * Overrides graphics to paint the drones and display telemetry data
         * above each drone object.
         */
        @Override
        protected void paintComponent(Graphics theGraphics) {
            super.paintComponent(theGraphics);

            theGraphics.setColor(Color.BLACK);

            for (Drone drone : myDrones) {
                int x = (int) Math.round(drone.getLongitude() * METERS_TO_PIXELS);
                int y = getHeight()
                    - (int) Math.round(drone.getLatitude() * METERS_TO_PIXELS);

                theGraphics.fillOval(
                    x - DRONE_RADIUS,
                    y - DRONE_RADIUS,
                    DRONE_RADIUS * 2,
                    DRONE_RADIUS * 2
                );

                String telemetry = String.format(
                    "D%d lon:%.1f lat:%.1f alt:%.0f h:%.0f",
                    drone.getID(),
                    drone.getLongitude(),
                    drone.getLatitude(),
                    drone.getAltitude(),
                    drone.getHeading()
                );

                FontMetrics metrics = theGraphics.getFontMetrics();
                int textWidth = metrics.stringWidth(telemetry);

                int textX = x - textWidth / 2;
                int textY = y - DRONE_RADIUS - 4;

                theGraphics.drawString(telemetry, textX, textY);
            }
        }
    }
}
