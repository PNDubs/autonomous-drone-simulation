/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360.view;

import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.tcss360.model.AnomalyRecord;
import com.tcss360.model.Drone;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.awt.event.ActionEvent;
import com.tcss360.controller.DroneMonitorApp;

import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.lang.StringBuilder;


/**
 * The MonitorDashboard class is the GUI for human-system interaction
 * @author Logan Black
 * @version 28 APR 2026
 */
public class MonitorDashboard {

    private DroneMonitorApp myApp;

    /** Table used to display drone information. */
    private JTable myDroneTable;

    /** Panel used to display the drone map view. */
    private JPanel myMapPanel;

    /** Text area used to display the alert log. */
    private JTextArea myAlertLog;

    /** Panel used for anomaly query results. */
    private JPanel myQjeryPanel;

    /**
     * Creates a monitor dashboard.
     */
    public MonitorDashboard() {

        /* Insert Logic Here */

    }

    /**
     * Sets the application controller used by the dashboard.
     *
     * @param theApp the application controller
     */
    public void setApp(final DroneMonitorApp theApp) {
        myApp = theApp;
    }

    /**
     * Displays the dashboard using the provided drone data.
     *
     * @param theDrones the drone fleet to display
     */
    public void display(ArrayList<Drone> theDrones) {

        /* Insert Logic Here */
        
    }

    /**
     * Adds an anomaly record to the alert log.
     *
     * @param theRecord the anomaly record to add
     */
    public void addAlert(AnomalyRecord theRecord) {

        /* Insert Logic Here */

    }

    /**
     * Updates the displayed telemetry information for the given drones.
     *
     * @param theDrones the drone fleet to update
     */
    private void updateDroneTelemetry(ArrayList<Drone> theDrones) {

        /* Insert Logic Here */

    }

    /**
     * Draws the current drone positions on the map panel.
     *
     * @param theDrones the drone fleet to draw
     */
    private void paintDronePositions(ArrayList<Drone> theDrones) {

        /* Insert Logic Here */

    }

    /**
     * Displays a query prompt and shows anomaly records for the selected drone.
     */
    private void showQueryScreen() {
        final String input = JOptionPane.showInputDialog(null, "Enter Drone ID:");

        if (input == null || input.isBlank()) {
            return;
        }

        try {
            final int droneID = Integer.parseInt(input);
            final ArrayList<AnomalyRecord> records = myApp.getAnomaliesForDrone(droneID);

            if (records.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No anomaly records found for Drone ID " + droneID);
            } else {
                final StringBuilder resultText = new StringBuilder();

                for (final AnomalyRecord record : records) {
                    resultText.append(record.toString()).append("\n\n");
                }

                JTextArea textArea = new JTextArea(resultText.toString(), 15, 40);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
                textArea.setEditable(false);

                JOptionPane.showMessageDialog(null, new JScrollPane(textArea),
                        "Query Results", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid integer Drone ID.");
        }


    }

    /**
     * Exports the alert log to a PDF file.
     *
     * @param theFilePath the path of the file to save
     */
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
     * Builds the dashboard menu bar.
     *
     * @return the created menu bar
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem exportPdfItem = new JMenuItem("Export Anomaly Log to PDF");
        exportPdfItem.addActionListener((ActionEvent e) -> exportAnomalyLogToPDF("anomaly_log.pdf"));
        fileMenu.add(exportPdfItem);
        menuBar.add(fileMenu);

        return menuBar;
    }

    /**
     * Handles dashboard exit behavior.
     */
    private void handleExit() {

        /* Insert Logic Here */


    }
}
