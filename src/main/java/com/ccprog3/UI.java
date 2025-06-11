package com.ccprog3;

/**
 * Interface for user display and input
 * @author Justin Ryan Uy
 */
public interface UI {
    /**
     * Login screen
     * @return login input string
     */
    public String login();
    /**
     * Login username not found
     * @author Justin Ryan Uy
     */
    public void loginErr(String username);
    
    /**
     * Main menu for the program. [1] Create a Coffee Truck; [2] Perform Coffee Truck features; [3] Dashboard; [x] Exit
     * @param username Username to display
     * @return Choice input
     * @author Justin Ryan Uy
     */
    public char mainMenu(String username);
}
