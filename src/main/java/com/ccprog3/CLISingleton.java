package com.ccprog3;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * CLI user interface
 * @author Justin Ryan Uy
 */
public class CLISingleton implements UI {
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
     * The user to interact with
     * @author Justin Ryan Uy
     */
    private final UserSingleton user = UserSingleton.getInstance();

    /**
     * Gets the CLI singleton instance
     * @return The instance
     * @author Justin Ryan Uy
     */
    public static CLISingleton getInstance() {
        return instance;
    }
    
    public void close() {
        sc.close();
        user.close();
    }
    
    /**
     * Automatically creates a radio-option display and input checking
     * @param options The possible options
     * @return The chosen option
     */
    private int radio(String... options) {
        while (true) {
            for (int i = 0; i < options.length; i++)
                System.out.println("[" + (i + 1) + "] " + options[i]);

            System.out.println("[b] Back\n[x] Exit");

            String input = input();

            // Input checking

            switch (input.toLowerCase()) {
                case "x":
                    return -1;
                    
                case "b":
                    return 0;
            }

            try {
                int inputParsed = Integer.parseInt(input);

                if (inputParsed >= 1 && inputParsed <= options.length)
                    return inputParsed;
            } catch (NumberFormatException e) {
                System.err.println("\n\u001b[41mInvalid input. Try again.\u001b[0m\n");
            }
        }
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

    /**
     * Double user input with text formatting
     * @return Input string
     * @author Justin Ryan Uy
     */
    private double inputDouble() {
        while (true) {
            System.out.print(": \u001b[4m");

            try {
                double input = sc.nextDouble();
                System.out.print("\u001b[0m");
                return input;
            } catch (InputMismatchException e) {
                System.out.print("\u001b[0m");
                System.err.println("\n\u001b[41mInput is not a number. Try again.\u001b[0m\n");
            }
        }
    }

    // UI

    public void login() {
        System.out.println("Login (Saves to a new user if not found)");
        
        try {
            user.login(input());
        } catch (FileNotFoundException e) {
            System.err.println("\n\u001b[41mUser not found. New user will be saved upon close.\u001b[0m\n");
        }
    }

    public void mainMenu() {
        // TODO Write better header
        System.out.println("\nWelcome, " + user.getUsername() + "!\n");

        while (true)
            switch (radio("Create a Coffee Truck", "Perform Coffee Truck features", "Dashboard")) {
                case 1:
                    
                    break;
            
                default:
                    break;
            }
    }
}
