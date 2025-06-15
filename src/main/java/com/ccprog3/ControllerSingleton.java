package com.ccprog3;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 * App controller
 * @author Justin Ryan Uy
 */
public class ControllerSingleton implements AutoCloseable {
    /**
     * Singleton instance of the Controller
     * @author Justin Ryan Uy
     */
    private static final ControllerSingleton instance = new ControllerSingleton();
    /**
     * The user to interact with
     * @author Justin Ryan Uy
     */
    private final UserSingleton user = UserSingleton.getInstance();

    /**
     * The UI for display
     * @author Justin Ryan Uy
     */
    private final UI ui = CLISingleton.getInstance();

    /**
     * Gets the Controller singleton instance
     * @return The instance
     * @author Justin Ryan Uy
     */
    public static ControllerSingleton getInstance() {
        return instance;
    }

    public void close() {
        user.close();
        if (ui instanceof CLISingleton) ((CLISingleton) ui).close();
    }

    /**
     * Typedef of HashMap<String, Supplier<Boolean>> for menu parameter
     * @author Justin Ryan Uy
     */
    private class Options extends HashMap<String, Supplier<Boolean>> {}

    /**
     * Handles menu options
     * @param options Options to choose from
     * @return false = back; true = exit
     * @author Justin Ryan Uy
     */
    private boolean menu(Options options) {
        String[] optionTexts = options.keySet().toArray(new String[0]);
        int choice;

        while ((choice = ui.radio(optionTexts)) != 0)
            if (choice == -1 || options.get(optionTexts[choice - 1]).get())
                return true;
                
        return false;
    }

    // Main
    
    /**
     * Logs the user in
     * @author Justin Ryan Uy
     */
    public void login() {
        try {
            user.login(ui.input("Login (Saves to a new user if not found)", "user"));
        } catch (FileNotFoundException e) {
            ui.displayErr(new FileNotFoundException("User not found. Will save to new user upon exit."));
        }

    }

    /**
     * Main menu for the program.
     * @author Justin Ryan Uy
     */
    public void mainMenu() {
        Options mainMenu = new Options();

        mainMenu.put("Create a Coffee Truck", () -> {
            createCoffeeTruck();
            return false;
        });
        mainMenu.put("Perform Coffee Truck features", () -> false);
        mainMenu.put("Dashboard", () -> false);

        menu(mainMenu);
    }
    
    /**
     * Asks the user for details on the Coffee Truck creation
     * @author Justin Ryan Uy
     */
    public void createCoffeeTruck() {
        Options specialInput = new Options();        
    }
}
