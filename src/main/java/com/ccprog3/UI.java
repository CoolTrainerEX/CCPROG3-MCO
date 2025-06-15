package com.ccprog3;

/**
 * UI for user display and input
 * @author Justin Ryan Uy
 */
public interface UI {
    /**
     * Radio display function
     * @param options Text options to display
     * @return Option number; 0 = back; -1 = exit
     * @author Justin Ryan Uy
     */
    public int radio(String... options);

    // UI

    /**
     * Login screen
     * @author Justin Ryan Uy
     */
    public String login();

    /**
     * User not found message
     * @author Justin Ryan Uy
     */
    public void loginErr();

    /**
     * Asks the user for details on the Coffee Truck creation
     * @return The created Coffee Truck
     * @author Justin Ryan Uy
     */
    public void createCoffeeTruck();
}
