package com.ccprog3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User class for the runtime instance
 * @author Justin Ryan Uy
 */
public class UserSingleton implements AutoCloseable {
    private static final UserSingleton instance = new UserSingleton();
    private final String username;
    private final List<CoffeeTruck> coffeeTrucks = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    /**
     * User constructor which logs the user in
     * @author Justin Ryan Uy
     */
    private UserSingleton() {
        System.out.print("Login (A new user will be created if the current user is not found): ");
        login(username = sc.nextLine());
    }

    /**
     * Gets the singleton instance
     * @return The instance
     */
    public static UserSingleton getInstance() {
        return instance;
    }

    /**
     * Closes the scanner and saves the user data to a file
     * @author Justin Ryan Uy
     */
    public void close() {
        sc.close();

        // TODO: Write file
    }

    /**
     * Logs the user in
     * @param username Username to log in with
     */
    public void login(String username) {
        try (Scanner filesc = new Scanner(new File(username))) {
            // TODO: read file
        } catch (FileNotFoundException e) {
            System.out.println("User not found. Will save to new user upon close.");
        }
    }

    /**
     * Main menu for the program
     * @return True if the menu requests to exit; false otherwise
     */
    public boolean mainMenu() {
        return true;
    }
}
