package com.ccprog3;

/**
 * Interface for user display and input
 * @author Justin Ryan Uy
 */
public interface UI extends AutoCloseable {
    public void close();

    /**
     * Login screen
     * @author Justin Ryan Uy
     */
    public void login();

    /**
     * Main menu for the program.
     * @author Justin Ryan Uy
     */
    public void mainMenu();
}
