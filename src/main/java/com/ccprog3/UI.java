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
     * Main menu for the program
     * @return Choice input
     * @author Justin Ryan Uy
     */
    public char mainMenu(String username);
}
