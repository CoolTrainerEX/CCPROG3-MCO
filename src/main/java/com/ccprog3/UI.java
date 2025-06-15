package com.ccprog3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 * UI class for user display and input
 * @author Justin Ryan Uy
 */
public abstract class UI implements AutoCloseable {
    /**
     * The user to interact with
     * @author Justin Ryan Uy
     */
    protected final UserSingleton user = UserSingleton.getInstance();

    public void close() {
        user.close();
    }

    /**
     * Radio display function to be called by menu()
     * @param options Text options to display
     * @return Option number; 0 = back; -1 = exit
     * @author Justin Ryan Uy
     */
    protected abstract int radio(String... options);

    /**
     * Handles menu options
     * @param options Options to choose from
     * @return false = back; true = exit
     * @author Justin Ryan Uy
     */
    private boolean menu(HashMap<String, Supplier<Boolean>> options) {
        String[] optionTexts = options.keySet().toArray(new String[0]);
        int choice;

        while ((choice = radio(optionTexts)) != 0)
            if (choice == -1 || options.get(optionTexts[choice - 1]).get())
                return true;
                
        return false;
    }

    // UI

    /**
     * Login screen
     * @author Justin Ryan Uy
     */
    public abstract void login();

    /**
     * Main menu for the program.
     * @author Justin Ryan Uy
     */
    public void mainMenu() {
        HashMap<String, Supplier<Boolean>> mainMenu = new HashMap<>();

        mainMenu.put("Create a Coffee Truck", () -> false);
        mainMenu.put("Perform Coffee Truck features", () -> false);
        mainMenu.put("Dashboard", () -> false);

        menu(mainMenu);
    }

    /**
     * Asks the user for details on the Coffee Truck creation
     * @return The created Coffee Truck
     */
    protected abstract void createCoffeeTruck();
}
