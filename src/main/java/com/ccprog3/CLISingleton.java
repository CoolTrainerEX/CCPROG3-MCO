package com.ccprog3;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * CLI user interface
 * 
 * @author Justin Ryan Uy
 */
public class CLISingleton implements UI, AutoCloseable {
    /**
     * Singleton instance of the CLI
     * 
     * @author Justin Ryan Uy
     */
    private static final CLISingleton instance = new CLISingleton();

    /**
     * System in Scanner to be used
     * 
     * @author Justin Ryan Uy
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Gets the CLI singleton instance
     * 
     * @return The instance
     * @author Justin Ryan Uy
     */
    public static CLISingleton getInstance() {
        return instance;
    }

    public void close() {
        sc.close();
    }

    /**
     * String user input with text formatting
     * 
     * @param prompt          Text prompt to display
     * @param defaultResponse Default return value if input is empty
     * @return Input string
     * @author Justin Ryan Uy
     */
    private String input(String prompt) {
        while (true) {
            System.out.print("\u001b[3;32m" + prompt + "\u001b[0m: \u001b[4m");

            String input = sc.nextLine();

            System.out.println("\u001b[0m");

            if (input != "")
                return input;

            displayErr(new InputMismatchException("Cannot be an empty string"));
        }
    }

    /**
     * Double user input with text formatting
     * 
     * @param prompt Text prompt to display
     * @return Input double
     * @author Justin Ryan Uy
     */
    private double inputNumber(String prompt) {
        while (true)
            try {
                return Double.parseDouble(input(prompt));
            } catch (NumberFormatException e) {
                displayErr(new InputMismatchException("Input is not a number. Try again."));
            }
    }

    /**
     * Dropdown display function
     * 
     * @param options Text options to display
     * @return Option number
     * @author Justin Ryan Uy
     */
    private int dropdown(String... options) {
        while (true) {
            System.out.print("\u001b[34m");

            for (int i = 0; i < options.length; i++)
                System.out.println("[" + (i + 1) + "] " + options[i]);

            System.out.print("\u001b[0m");

            double input = inputNumber("");

            // Input checking
            if (input != (int) input) {
                displayErr(new InputMismatchException("Input is not an integer. Try again."));
                continue;
            }

            if (input >= 1 && input <= options.length)
                return (int) input;

            displayErr(new InputMismatchException("Invalid input. Try again."));
        }
    }

    /**
     * Formats money float values into proper Strings
     * 
     * @param money The value
     * @return Formatted string
     */
    private String formatMoney(float money) {
        return String.format("$%.2f", money);
    }

    public int menu(String... options) {
        while (true) {
            System.out.print("\u001b[34m");

            for (int i = 0; i < options.length; i++)
                System.out.println("[" + (i + 1) + "] " + options[i]);

            System.out.println("[b] Back\n[x] Exit\u001b[0m");

            String input = input("");

            // Input checking

            switch (input.toLowerCase()) {
                case "x":
                    return -1;

                case "b":
                    return 0;
            }

            try {
                int inputParsed = Integer.parseInt(input);

                if (inputParsed >= 1 && inputParsed <= options.length)
                    return inputParsed;
            } catch (NumberFormatException e) {
            }

            displayErr(new InputMismatchException("Invalid input. Try again."));

        }
    }

    public void displayErr(Exception e) {
        System.err.println("\n\u001b[41m" + e.getLocalizedMessage() + "\u001b[0m\n");
    }

    public String login() {
        return input("Login (Saves to a new user if not found)");
    }

    public CoffeeTruck addCoffeeTruck() {
        boolean special = dropdown("Regular Coffee Truck", "Special Coffee Truck") == 2;
        String location = input("Location");
        StorageBin[] storageBins = new StorageBin[8];

        for (int i = 0; i < storageBins.length; i++)
            storageBins[i] = setStorageBin(false);

        if (!special)
            return new CoffeeTruck(location, storageBins);

        // Special

        SpecialStorageBin[] specialStorageBins = new SpecialStorageBin[2];

        for (int i = 0; i < specialStorageBins.length; i++)
            specialStorageBins[i] = (SpecialStorageBin) setStorageBin(true);

        return new SpecialCoffeeTruck(location, storageBins, specialStorageBins);
    }

    public void coffeeTruckInfo(CoffeeTruck coffeeTruck) {
        System.out.println("\n\u001b[1;44m" + coffeeTruck + "\u001b[0;32m\n");

        for (StorageBin storageBin : coffeeTruck.getStorageBins())
            System.out.println(storageBin);

        System.out.print("\u001b[3;31m");

        for (Coffee coffee : coffeeTruck.getSales().keySet())
            System.out.println(coffee + ": " + formatMoney(coffeeTruck.getSales().get(coffee)));

        // TODO add Ingredients
        // TODO add Menu

        System.out.println("\u001b[0m");
    }

    public int chooseCoffeeTruck(List<CoffeeTruck> coffeeTrucks) {
        return dropdown(coffeeTrucks.toArray(String[]::new)) - 1;
    }

    public int chooseStorageBin(List<StorageBin> storageBins) {
        return dropdown(storageBins.toArray(String[]::new)) - 1;
    }

    public double addStorageBinQuantity() {
        return inputNumber("Add quantity");
    }

    public StorageBin setStorageBin(boolean special) {
        if (!special) {

            while (true)
                try {
                    Ingredient ingredient;

                    return new StorageBin(
                            ingredient = Ingredient.values()[dropdown(
                                    Arrays.stream(Ingredient.values()).map(Enum::toString).toArray(String[]::new)) - 1],
                            ingredient == Ingredient.NONE ? 0 : inputNumber("Quantity"));
                } catch (ArithmeticException e) {
                    displayErr(e);
                }
        }

        while (true)
            try {
                SyrupIngredient syrupIngredient;

                return new SpecialStorageBin(syrupIngredient = SyrupIngredient.values()[dropdown(
                        Arrays.stream(SyrupIngredient.values()).map(Enum::toString).toArray(String[]::new)) - 1],
                        syrupIngredient == SyrupIngredient.NONE ? 0 : inputNumber("Quantity"));
            } catch (ArithmeticException e) {
                displayErr(e);
            }
    }

    public String setCoffeeTruckLocation() {
        return input("Set new location");
    }
}
