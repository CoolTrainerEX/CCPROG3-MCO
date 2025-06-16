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

    @Override
    public int menu(String... options) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'radio'");
    }

    @Override
    public void displayErr(Exception e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayErr'");
    }

    @Override
    public String login() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public CoffeeTruck addCoffeeTruck(boolean special) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCoffeeTruck'");
    }
}
