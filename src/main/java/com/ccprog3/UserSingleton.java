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
    /**
     * Singleton instance of the User
     * @author Justin Ryan Uy
     */
    private static final UserSingleton instance = new UserSingleton();

    /**
     * Username to be used for reading and writing to files
     * @author Justin Ryan Uy
     */
    private final String username;

    /**
     * List of Coffee Trucks made by the User
     * @author Justin Ryan Uy
     */
    private final List<CoffeeTruck> coffeeTrucks = new ArrayList<>();

    /**
     * System in Scanner used by the User
     * @author Justin Ryan Uy
     */
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
     * @author Justin Ryan Uy
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
     * @author Justin Ryan Uy
     */
    private void login(String username) {
        try (Scanner filesc = new Scanner(new File(username))) {
            // TODO: read file
        } catch (FileNotFoundException e) {
            System.out.println("User not found. Will save to new user upon close.");
        }
    }

    /**
     * Main menu for the program
     * @return True if the menu requests to exit; false otherwise
     * @author Justin Ryan Uy
     */
    public boolean mainMenu() {
        return true;
    }
}
