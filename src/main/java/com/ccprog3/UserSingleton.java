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
     * UI to be used
     * @author Justin Ryan Uy
     */
    private final UI ui = CLISingleton.getInstance();

    /**
     * User constructor which logs the user in
     * @author Justin Ryan Uy
     */
    private UserSingleton() {
        login(username = ui.login());
    }

    /**
     * Gets the User singleton instance
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
        if (ui instanceof CLISingleton) ((CLISingleton) ui).close();

        // TODO: Write file
    }

    /**
     * Logs the user in (opens save file)
     * @param username Username to log in with
     * @author Justin Ryan Uy
     */
    private void login(String username) {
        try (Scanner filesc = new Scanner(new File(username))) {
            // TODO: read file
        } catch (FileNotFoundException e) {
            ui.loginErr(username);
        }
    }

    // User commands

    /**
     * User main menu
     * @return true if user requests exit; false otherwise
     * @author Justin Ryan Uy
     */
    public boolean mainMenu() {
        switch (ui.mainMenu(username)) {
            case '1':
                
                break;
        
            case '2':
                
                break;
        
            case '3':
                
                break;
            
            case 'x':
                return true;
        }

        return false;
    }
}
