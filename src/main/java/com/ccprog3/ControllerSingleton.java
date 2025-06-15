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
     * Handles menu options
     * @param options Options to choose from
     * @return false = back; true = exit
     * @author Justin Ryan Uy
     */
    private boolean menu(HashMap<String, Supplier<Boolean>> options) {
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
            user.login(ui.login());
        } catch (FileNotFoundException e) {
            ui.loginErr();
        }

    }

    /**
     * Main menu for the program.
     * @author Justin Ryan Uy
     */
    public void mainMenu() {
        HashMap<String, Supplier<Boolean>> mainMenu = new HashMap<>();

        mainMenu.put("Create a Coffee Truck", () -> {
            return false;
        });
        mainMenu.put("Perform Coffee Truck features", () -> false);
        mainMenu.put("Dashboard", () -> false);

        menu(mainMenu);
    }
}
