package com.ccprog3;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.function.Supplier;

import com.ccprog3.coffee.CoffeeType;
import com.ccprog3.coffee.Espresso;
import com.ccprog3.coffeeTruck.CoffeeTruck;
import com.ccprog3.coffeeTruck.SpecialCoffeeTruck;
import com.ccprog3.coffeeTruck.SpecialStorageBin;
import com.ccprog3.coffeeTruck.StorageBin;
import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;

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
     * Handles menu options
     * 
     * @param menu Map of menu options and their corresponding actions
     * @return false = back; true = exit
     * @author Justin Ryan Uy
     */
    private boolean menu(Map<String, Supplier<Boolean>> menu) {
        String[] optionTexts = menu.keySet().toArray(String[]::new);
        int choice;

        while ((choice = ui.menu(optionTexts)) != 0)
            while (true)
                try {
                    if (choice == -1 || menu.get(optionTexts[choice - 1]).get())
                        return true;

                    break;
                } catch (Exception e) {
                    ui.displayErr(e);
                }

        return false;
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
        menu(Map.of(
                "Create a Coffee Truck", () -> {
                    user.addCoffeeTruck(ui.addCoffeeTruck());
                    return false;
                },

                "Perform Coffee Truck features", () -> {
                    if (user.getCoffeeTrucks().size() == 0) {
                        ui.displayErr(new NullPointerException("No Coffee Trucks exist"));
                        return false;
                    }

                    int chosenCoffeeTruckIndex = ui.chooseCoffeeTruck(user.getCoffeeTrucks());
                    CoffeeTruck chosenCoffeeTruck = user.getCoffeeTrucks().get(chosenCoffeeTruckIndex);

                    return menu(Map.of(
                            "Buy a Coffee", () -> {
                                ui.makeCoffee(chosenCoffeeTruck.makeCoffee(ui.buyCoffee(chosenCoffeeTruck instanceof SpecialCoffeeTruck), user));
                                return false;
                            },

                            "View truck information", () -> {
                                ui.coffeeTruckInfo(chosenCoffeeTruck, user);
                                return false;
                            },

                            "Restocking and maintainance", () -> {
                                return menu(Map.of(
                                        "Restocking", () -> {
                                            int chosenStorageBinIndex = ui
                                                    .chooseStorageBin(chosenCoffeeTruck.getStorageBins());
                                            StorageBin chosenStorageBin = chosenCoffeeTruck.getStorageBins()
                                                    .get(chosenStorageBinIndex);

                                            return menu(Map.of(
                                                    "Replenish Storage Bin", () -> {
                                                        if (!(chosenStorageBin instanceof SpecialStorageBin)
                                                                && chosenStorageBin.getIngredient() == Ingredient.NONE
                                                                || chosenStorageBin instanceof SpecialStorageBin
                                                                        && ((SpecialStorageBin) chosenStorageBin)
                                                                                .getSyrupIngredient() == SyrupIngredient.NONE) {
                                                            ui.displayErr(new NullPointerException(
                                                                    "Storage Bin contains no Ingredient"));
                                                            return false;
                                                        }

                                                        chosenStorageBin.addQuantity(ui.addStorageBinQuantity());
                                                        return false;
                                                    },

                                                    "Replace with a different Ingredient", () -> {
                                                        chosenCoffeeTruck.setStorageBin(
                                                                ui.setStorageBin(
                                                                        chosenStorageBin instanceof SpecialStorageBin),
                                                                chosenStorageBinIndex);
                                                        return false;
                                                    },

                                                    "Empty Storage Bin", () -> {
                                                        chosenCoffeeTruck.emptyStorageBin(chosenStorageBinIndex);
                                                        return false;
                                                    }));
                                        },

                                        "Maintainance", () -> {
                                            return menu(Map.of(
                                                    "Change truck location", () -> {
                                                        user.setCoffeeTruckLocation(ui.setCoffeeTruckLocation(),
                                                                chosenCoffeeTruckIndex);
                                                        return false;
                                                    },

                                                    "Change product prices", () -> {
                                                        return menu(Map.of(
                                                                "Change Coffee prices", () -> {
                                                                    user.setCoffeePrices(
                                                                            ui.setPrices(CoffeeType.class));
                                                                    return false;
                                                                },

                                                                "Change Espresso prices", () -> {
                                                                    user.setEspressoPrices(
                                                                            ui.setPrices(Espresso.class));
                                                                    return false;
                                                                },

                                                                "Change Syrup prices", () -> {
                                                                    user.setSyrupPrices(
                                                                            ui.setPrices(SyrupIngredient.class));
                                                                    return false;
                                                                }));
                                                    }));
                                        }));
                            }));
                },

                "Dashboard", () -> {
                    ui.dashboard(user.getCoffeeTrucks());
                    return false;
                }));
    }
}
