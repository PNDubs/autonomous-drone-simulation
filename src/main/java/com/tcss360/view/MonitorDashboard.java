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

/**
 * The MonitorDashboard class is the GUI for human-system interaction
 * @author Logan Black
 * @version 28 APR 2026
 */
public class MonitorDashboard {

    /** The drone table */
    private JTable myDroneTable;

    /** The drone map panel */
    private JPanel myMapPanel;

    /** The alert log area */
    private JTextArea myAlertLog;

    /** The alert log query area */
    private JPanel myQjeryPanel;

    /**
     * Constructor
     */
    public MonitorDashboard() {

        /* Insert Logic Here */

    }

    /**
     * 
     * @param theDrones the drone fleet
     */
    public void display(ArrayList<Drone> theDrones) {

        /* Insert Logic Here */
        
    }

    /**
     * 
     * @param theRecord an anomaly record
     */
    public void addAlert(AnomalyRecord theRecord) {

        /* Insert Logic Here */

    }

    /**
     * 
     * @param theDrones the drone fleet
     */
    private void updateDroneTelemetry(ArrayList<Drone> theDrones) {

        /* Insert Logic Here */

    }

    /**
     * 
     * @param theDrones the drone fleet
     */
    private void paintDronePositions(ArrayList<Drone> theDrones) {

        /* Insert Logic Here */

    }

    /**
     * 
     */
    private void showQueryScreen() {

        /* Insert Logic Here */

    }

    /**
     * 
     * @param theFilePath the file save path
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
     * 
     * @return
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem exportPdfItem = new JMenuItem("Export Anomaly Log to PDF");
        exportPdfItem.addActionListener(e -> exportAnomalyLogToPDF("anomaly_log.pdf"));

        fileMenu.add(exportPdfItem);
        menuBar.add(fileMenu);

        return menuBar;
    }

    /**
     * 
     */
    private void handleExit() {

        /* Insert Logic Here */


    }
}
