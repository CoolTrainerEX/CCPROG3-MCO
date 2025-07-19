package com.ccprog3;

import java.io.FileNotFoundException;
import java.util.Map;

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
     */
    private static final ControllerSingleton instance = new ControllerSingleton();
    /**
     * The user to interact with
     */
    private final UserSingleton user = UserSingleton.getInstance();

    /**
     * The {@code UI} for display
     */
    private final UI ui = GUISingleton.getInstance();

    /**
     * Default constructor
     */
    private ControllerSingleton() {
    }

    /**
     * Gets the {@code ControllerSingleton} instance
     * 
     * @return The instance
     */
    public static ControllerSingleton getInstance() {
        return instance;
    }

    @Override
    public void close() {
        user.close();
        if (ui instanceof CLISingleton)
            ((CLISingleton) ui).close();
    }

    // Main

    /**
     * Logs the user in
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
     */
    public void mainMenu() {
        ui.menu(Map.of(
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

                    return ui.menu(Map.of(
                            "Buy a Coffee", () -> {
                                ui.makeCoffee(chosenCoffeeTruck.makeCoffee(
                                        ui.buyCoffee(chosenCoffeeTruck instanceof SpecialCoffeeTruck), user));
                                return false;
                            },

                            "View truck information", () -> {
                                ui.coffeeTruckInfo(chosenCoffeeTruck, user);
                                return false;
                            },

                            "Restocking and maintainance", () -> {
                                return ui.menu(Map.of(
                                        "Restocking", () -> {
                                            int chosenStorageBinIndex = ui
                                                    .chooseStorageBin(chosenCoffeeTruck.getStorageBins());
                                            StorageBin chosenStorageBin = chosenCoffeeTruck.getStorageBins()
                                                    .get(chosenStorageBinIndex);

                                            return ui.menu(Map.of(
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
                                            return ui.menu(Map.of(
                                                    "Change truck location", () -> {
                                                        user.setCoffeeTruckLocation(ui.setCoffeeTruckLocation(),
                                                                chosenCoffeeTruckIndex);
                                                        return false;
                                                    },

                                                    "Change product prices", () -> {
                                                        return ui.menu(Map.of(
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
