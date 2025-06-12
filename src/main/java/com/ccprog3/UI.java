package com.ccprog3;

/**
 * Interface for user display and input
 * @author Justin Ryan Uy
 */
public interface UI {
    /**
     * Login screen
     * @author Justin Ryan Uy
     */
    public void login();

    /**
     * Main menu for the program.
     * @param username Username to display
     * @author Justin Ryan Uy
     */
    public void mainMenu(String username);
}
