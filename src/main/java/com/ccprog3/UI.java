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

    
    /**
     * String user input with text formatting
     * @param prompt Text prompt to display
     * @param defaultResponse Default return value if input is empty
     * @return Input string
     * @author Justin Ryan Uy
     */
    public String input(String prompt, String defaultResponse);

    /**
     * Double user input with text formatting
     * @param prompt Text prompt to display
     * @return Input double
     * @author Justin Ryan Uy
     */
    public double inputDouble(String prompt);

    /**
     * Displays error message
     * @param e Exception
     */
    public void displayErr(Exception e);
}
