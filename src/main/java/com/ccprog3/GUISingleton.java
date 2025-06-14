package com.ccprog3;

/**
 * GUI user interface
 * @author Justin Ryan Uy
 */
public class GUISingleton extends UI {
    /**
     * Singleton instance of the GUI
     * @author Justin Ryan Uy
     */
    private static final GUISingleton instance = new GUISingleton();

    /**
     * Gets the GUI singleton instance
     * @return The instance
     * @author Justin Ryan Uy
     */
    public static GUISingleton getInstance() {
        return instance;
    }
    
    /**
     * Automatically creates a radio-option display and input checking. 0 = Back; -1 = Exit.
     * @param options The possible options
     * @return The chosen option
     */
    protected int radio(String... options) {
        return 0;
    }

    // UI

    public void login() {}

    protected boolean createCoffeeTruck() { return false; }
}
