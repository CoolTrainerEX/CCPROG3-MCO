package com.ccprog3;

import java.util.Scanner;

/**
 * CLI user interface
 * @author Justin Ryan Uy
 */
public class CLISingleton implements UI, AutoCloseable {
    /**
     * Singleton instance of the CLI
     * @author Justin Ryan Uy
     */
    private static final CLISingleton instance = new CLISingleton();

    /**
     * System in Scanner to be used
     * @author Justin Ryan Uy
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Gets the CLI singleton instance
     * @return The instance
     * @author Justin Ryan Uy
     */
    public static CLISingleton getInstance() {
        return instance;
    }

    /**
     * Closes the scanner
     * @author Justin Ryan Uy
     */
    public void close() {
        sc.close();
    }

    /**
     * String user input with text formatting
     * @return Input string
     * @author Justin Ryan Uy
     */
    private String input() {
        System.out.print(": \u001b[4m");

        String input = sc.nextLine();

        System.out.print("\u001b[0m");

        return input;
    }

    // UI

    public String login() {
        System.out.println("Login (Saves to a new user if not found)");
        return input();
    }

    public void loginErr(String username) {
        System.err.println("User not found. New user will be saved upon close");
    }
    
    public char mainMenu(String username) {
        System.out.println("\nWelcome, " + username + "!\n");

        System.out.println("""
                [1] Create a Coffee Truck
                [2] Perform Coffee Truck features
                [3] Dashboard
                [x] Exit
                """);

        switch (sc.nextLine()) {
            case "1":
                
                break;
        
            default:
                break;
        }
        return 'a';
    }
}
