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
     * Typedef of HashMap<String, Supplier<Boolean>> for menu parameter. Supplier return determines exit.
     * @author Justin Ryan Uy
     */
    private class Menu extends HashMap<String, Supplier<Boolean>> {
        /**
         * Handles menu options
         * @return false = back; true = exit
         * @author Justin Ryan Uy
         */
        private boolean display() {
            String[] optionTexts = keySet().toArray(new String[0]);
            int choice;

            while ((choice = ui.menu(optionTexts)) != 0)
                if (choice == -1 || get(optionTexts[choice - 1]).get())
                    return true;
                    
            return false;
        }
    }

    // Main
    
    /**
     * Logs the user in
     * @author Justin Ryan Uy
     */
    public void login() {
        try {
            user.login(ui.login());
        } catch (FileNotFoundException e) {
            ui.displayErr(new FileNotFoundException("User not found. Will save to new user upon exit."));
        }

    }

    /**
     * Main menu for the program.
     * @author Justin Ryan Uy
     */
    public void mainMenu() {
        Menu mainMenu = new Menu();

        mainMenu.put("Create a Coffee Truck", () -> {
            Menu special = new Menu();

            special.put("Regular Coffee Truck", () -> {
                while (true)
                    try {
                        user.addCoffeeTruck(ui.addCoffeeTruck(false));
                        return false;
                    } catch (IllegalArgumentException e) {
                        ui.displayErr(e);
                    }
            });
            special.put("Special Coffee Truck", () -> {
                while (true)
                    try {
                        user.addCoffeeTruck(ui.addCoffeeTruck(true));
                        return false;
                    } catch (IllegalArgumentException e) {
                        ui.displayErr(e);
                    }
            });

            return special.display();
        });
        mainMenu.put("Perform Coffee Truck features", () -> false);
        mainMenu.put("Dashboard", () -> false);

        mainMenu.display();
    }
}
