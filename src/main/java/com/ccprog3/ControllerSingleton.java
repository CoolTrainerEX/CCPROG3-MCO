package com.ccprog3;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 * App controller
 * 
 * @author Justin Ryan Uy
 */
public class ControllerSingleton implements AutoCloseable {
    /**
     * Singleton instance of the Controller
     * 
     * @author Justin Ryan Uy
     */
    private static final ControllerSingleton instance = new ControllerSingleton();
    /**
     * The user to interact with
     * 
     * @author Justin Ryan Uy
     */
    private final UserSingleton user = UserSingleton.getInstance();

    /**
     * The UI for display
     * 
     * @author Justin Ryan Uy
     */
    private final UI ui = CLISingleton.getInstance();

    /**
     * Gets the Controller singleton instance
     * 
     * @return The instance
     * @author Justin Ryan Uy
     */
    public static ControllerSingleton getInstance() {
        return instance;
    }

    public void close() {
        user.close();
        if (ui instanceof CLISingleton)
            ((CLISingleton) ui).close();
    }

    /**
     * Menu display on the UI. Supplier return determines exit.
     * 
     * @author Justin Ryan Uy
     */
    private class Menu extends HashMap<String, Supplier<Boolean>> {
        /**
         * Handles menu options
         * 
         * @return false = back; true = exit
         * @author Justin Ryan Uy
         */
        private boolean display() {
            String[] optionTexts = keySet().toArray(new String[0]);
            int choice;

            while ((choice = ui.menu(optionTexts)) != 0)
                while (true)
                    try {
                        if (choice == -1 || get(optionTexts[choice - 1]).get())
                            return true;

                        break;
                    } catch (Exception e) {
                        ui.displayErr(e);
                    }

            return false;
        }
    }

    // Main

    /**
     * Logs the user in
     * 
     * @author Justin Ryan Uy
     */
    public void login() {
        try {
            user.login(ui.login());
        } catch (FileNotFoundException e) {
            ui.displayErr(e);
        }

    }

    /**
     * Main menu for the program.
     * 
     * @author Justin Ryan Uy
     */
    public void mainMenu() {
        Menu mainMenu = new Menu();

        mainMenu.put("Create a Coffee Truck", () -> {
            Menu special = new Menu();

            special.put("Regular Coffee Truck", () -> {
                user.addCoffeeTruck(ui.addCoffeeTruck(false));
                return false;
            });
            special.put("Special Coffee Truck", () -> {
                user.addCoffeeTruck(ui.addCoffeeTruck(true));
                return false;
            });

            return special.display();
        });
        mainMenu.put("Perform Coffee Truck features", () -> {
            if (user.getCoffeeTrucks().length == 0) {
                ui.displayErr(new NullPointerException("No Coffee Trucks exist"));
                return false;
            }

            Menu features = new Menu();

            // Ask which truck
            int chosenCoffeeTruckIndex = ui.chooseCoffeeTruck(user.getCoffeeTrucks());
            CoffeeTruck chosenCoffeeTruck = user.getCoffeeTrucks()[chosenCoffeeTruckIndex];

            features.put("Buy a coffee", null);
            features.put("View truck information", () -> {
                ui.coffeeTruckInfo(chosenCoffeeTruck);
                return false;
            });
            features.put("Restocking and maintainance", () -> {
                Menu misc = new Menu();

                misc.put("Restocking", () -> {
                    Menu restock = new Menu();

                    int chosenStorageBinIndex = ui.chooseStorageBin(chosenCoffeeTruck.getStorageBins());

                    restock.put("Replenish Storage Bin", () -> {
                        chosenCoffeeTruck.addStorageBinQuantity(ui.storageBinAddQuantity(), chosenStorageBinIndex);
                        return false;
                    });
                    restock.put("Replace with a different Ingredient", () -> {
                        chosenCoffeeTruck.setStorageBin(ui.setStorageBin(
                                chosenCoffeeTruck.getStorageBins()[chosenStorageBinIndex] instanceof SpecialStorageBin),
                                chosenStorageBinIndex);
                        return false;
                    });
                    restock.put("Empty Storage Bin", () -> {
                        chosenCoffeeTruck.emptyStorageBin(chosenStorageBinIndex);
                        return false;
                    });

                    return restock.display();
                });
                misc.put("Maintainance", () -> {
                    Menu maintainance = new Menu();

                    maintainance.put("Change truck location", () -> {
                        user.setCoffeeTruckLocation(ui.setCoffeeTruckLocation(), chosenCoffeeTruckIndex);
                        return false;
                    });
                    maintainance.put("Change product prices", null);

                    return maintainance.display();
                });

                return misc.display();
            });

            return features.display();
        });
        mainMenu.put("Dashboard", () -> false);

        mainMenu.display();
    }
}
