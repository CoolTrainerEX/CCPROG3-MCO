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
    private String username;

    /**
     * List of Coffee Trucks made by the User
     * @author Justin Ryan Uy
     */
    private final List<CoffeeTruck> coffeeTrucks = new ArrayList<>();
    
    /**
     * Gets the User singleton instance
     * @return The instance
     * @author Justin Ryan Uy
     */
    public static UserSingleton getInstance() {
        return instance;
    }

    /**
     * Gets the username
     * @return Username
     * @author Justin Ryan Uy
     */
    public String getUsername() {
        return username;
    }

    /**
     * Saves the user data to a file
     * @author Justin Ryan Uy
     */
    public void close() {
        // TODO: Write file
    }

    /**
     * Logs the user in (opens save file)
     * @param username Username to log in with
     * @author Justin Ryan Uy
     */
    public void login(String username) throws FileNotFoundException {
        this.username = username;

        try (Scanner filesc = new Scanner(new File(username))) {
            // TODO: read file
        }
    }

    public void addCoffeeTruck() {
        coffeeTrucks.add(null);
    }
}
