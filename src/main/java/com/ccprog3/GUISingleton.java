package com.ccprog3;

/**
 * GUI user interface
 * @author Justin Ryan Uy
 */
public class GUISingleton implements UI {
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
}
