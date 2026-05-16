/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.tcss360.model.AnomalyRecord;
import com.tcss360.model.Drone;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * The MonitorDashboard class is the GUI for human-system interaction
 * @author Logan Black
 * @version 15 May 2026
 */
public class MonitorDashboard {

    /** Root panel for display inside myFrame */
    private final JPanel myRootPanel;

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
     * Helper method to display the drones
     * @param theDrones the drone fleet
     */
    public void display(ArrayList<Drone> theDrones) {

        myMapPanel.setDrones(theDrones);

    }

    /**
     * Helper method to update anomaly text area
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
     * Helper method to build the menu bar
     * @return completed JMenuBar
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem saveCSVItem = new JMenuItem("Save Anomaly Log to CSV");
        JMenuItem exportPdfItem = new JMenuItem("Export Anomaly Log to PDF");
        exportPdfItem.addActionListener(e -> exportAnomalyLogToPDF("anomaly_log.pdf"));
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event -> handleExit());

        fileMenu.add(saveCSVItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        fileMenu.addSeparator();
        fileMenu.add(exportPdfItem);


        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem instructionsItem = new JMenuItem("Instructions");
        aboutItem.addActionListener(event -> showAboutDialog());
        instructionsItem.addActionListener(event -> showInstructionsDialog());

        helpMenu.add(aboutItem);
        helpMenu.add(instructionsItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void exportAnomalyLogToPDF(String theFilePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(50, 750);

            String logText = (myAlertLog == null) ? "" : myAlertLog.getText();
            if (logText == null || logText.isEmpty()) {
                contentStream.showText("No anomaly log entries available.");
            } else {
                String[] lines = logText.split("\\n");
                int lineCount = 0;

                for (String line : lines) {
                    if (lineCount >= 45) {
                        break;
                    }
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -15);
                    lineCount++;
                }
            }

            contentStream.endText();
            contentStream.close();

            document.save(theFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    /**
     * Shows basic project information.
     */
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(myRootPanel, """
                                                   Autonomous Drone Simulation
                                                   TCSS 360 Course Project - Spring 2026
                                                   
                                                   A Java-based Maven project that simulates a fleet of
                                                   drones and random anomalous behavior employing
                                                   object-oriented programming principles and
                                                   collaborativedevelopment using YouTrack and GitHub.
                                                   
                                                   Authors: Logan Black, Ibrahim Cartan, Matthew Park""",
            "About",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Shows basic dashboard instructions.
     */
    private void showInstructionsDialog() {
        JOptionPane.showMessageDialog(myRootPanel, """
                                                   Map displays the fleet of drones

                                                   Alerts dispalys all anomaly alerts
                                                   
                                                   Query allows for the display of specific 
                                                   anomaly logs as selected by the user. 
                                                   Select a query to display it.""",
            "Instructions",
            JOptionPane.INFORMATION_MESSAGE
        );
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

        switch (choice) {
            case 0 ->                 {
                    String input = JOptionPane.showInputDialog("Enter Drone ID:");
                    if (input != null) {
                        JOptionPane.showMessageDialog(null, "Querying anomalies for Drone ID: " + input);
                    }                      }
            case 1 ->                 {
                    String input = JOptionPane.showInputDialog("Enter Anomaly Type (e.g. LOW_BATTERY):");
                    if (input != null) {
                        JOptionPane.showMessageDialog(null, "Querying anomalies of type: " + input);
                    }                      }
            case 2 -> {
                String start = JOptionPane.showInputDialog("Enter start time (yyyy-MM-ddTHH:mm:ss):");
                String end = JOptionPane.showInputDialog("Enter end time (yyyy-MM-ddTHH:mm:ss):");
                if (start != null && end != null) {
                    JOptionPane.showMessageDialog(null, "Querying anomalies between: " + start + " and " + end);
                }
            }
            default -> {
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
            try (java.io.FileWriter writer = new java.io.FileWriter(theFilePath)) {
                writer.write("Timestamp,DroneID,AnomalyType,AnomalyDetails\n");
                writer.write(logText);
            }
        } catch (java.io.IOException e) {
            System.err.println("An error has occurred while exporting the Anomaly Log to CSV " + e);
        }

    }

    /**
     * Helper method for handling exit operations
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
     * An inner class to act as a custom JPanel for drawing drone positions.
     */
    private final class DroneMapPanel extends JPanel {

        /** The radius of a drone object on the map in px */
        private static final int DRONE_RADIUS = 2;

        /** The radius of a drone trail point on the map in px */
        private static final int TRAIL_RADIUS = 1;

        /** The maximum number of trail points to keep per drone */
        private static final int MAX_TRAIL_POINTS = 800;

        /** The conversion rate from 1 meter to # px */
        private static final double METERS_TO_PIXELS = 4.0;

        /** The drones to paint */
        private ArrayList<Drone> myDrones = new ArrayList<>();

        /** The drone trails keyed by drone ID */
        private final Map<Integer, ArrayList<Point>> myDroneTrails = new HashMap<>();

        /**
         * Sets the drone positions and repaints them
         * @param theDrones the list of drones
         */
        private void setDrones(ArrayList<Drone> theDrones) {
            myDrones = theDrones;

            for (Drone drone : myDrones) {
                int x = (int) Math.round(drone.getLongitude() * METERS_TO_PIXELS);
                int y = getHeight()
                    - (int) Math.round(drone.getLatitude() * METERS_TO_PIXELS);

                ArrayList<Point> trail = myDroneTrails.computeIfAbsent(
                    drone.getID(),
                    id -> new ArrayList<>()
                );

                trail.add(new Point(x, y));

                if (trail.size() > MAX_TRAIL_POINTS) {
                    trail.remove(0);
                }
            }

            repaint();
        }

        /**
         * Overrides graphics to paint the drones and display telemetry data
         * above each drone object. A trail follows each drone for easy visualization of previously
         * visited points
         */
        @Override
        protected void paintComponent(Graphics theGraphics) {
            super.paintComponent(theGraphics);

            Graphics2D g2 = (Graphics2D) theGraphics.create();

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0875f));
            g2.setColor(new Color(255, 120, 120));

            for (ArrayList<Point> trail : myDroneTrails.values()) {
                for (Point point : trail) {
                    g2.fillOval(
                        point.x - TRAIL_RADIUS,
                        point.y - TRAIL_RADIUS,
                        TRAIL_RADIUS * 2,
                        TRAIL_RADIUS * 2
                    );
                }
            }

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.setColor(Color.BLACK);

            for (Drone drone : myDrones) {
                int x = (int) Math.round(drone.getLongitude() * METERS_TO_PIXELS);
                int y = getHeight()
                    - (int) Math.round(drone.getLatitude() * METERS_TO_PIXELS);

                g2.fillOval(
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

                FontMetrics metrics = g2.getFontMetrics();
                int textWidth = metrics.stringWidth(telemetry);

                int textX = x - textWidth / 2;
                int textY = y - DRONE_RADIUS - 4;

                g2.drawString(telemetry, textX, textY);
            }

            g2.dispose();
        }
    }
}
