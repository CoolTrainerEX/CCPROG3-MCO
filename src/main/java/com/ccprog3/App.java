package com.ccprog3;

/**
 * Driver class
 * 
 * @author Justin Ryan Uy
 */
public class App {
    /**
     * Main function entry point
     * 
     * @param args Args
     */
    public static void main(String[] args) {
        try (ControllerSingleton controller = ControllerSingleton.getInstance()) {
            controller.login();
            controller.mainMenu();
        }
    }

    /**
     * Default constructor
     */
    public App() {
    }
}
