/*
 * Course Project
 * TCSS 360 Spring 2026
 * Logan Black
 */

package com.tcss360;

import com.tcss360.controller.DroneMonitorApp;

/**
 * The Main class for the autonomous drone simulation program
 * @author Logan Black
 * @version  10 May 2026
 */
public class Main {

    /**
     * The main entry point for program start
     * @param theArgs not used
     */
    public static void main(String[] theArgs) {

        new DroneMonitorApp().start();

    }
}
