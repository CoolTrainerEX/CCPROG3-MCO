package com.ccprog3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 * CLI user interface
 * @author Justin Ryan Uy
 */
public class CLISingleton implements UI, AutoCloseable {
    /**
     * Singleton instance of the CLI
     * @author Justin Ryan Uy
     */
    private static final CLISingleton instance = new CLISingleton();

    /**
     * System in Scanner to be used
     * @author Justin Ryan Uy
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Gets the CLI singleton instance
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
     * @param prompt Text prompt to display
     * @param defaultResponse Default return value if input is empty
     * @return Input string
     * @author Justin Ryan Uy
     */
    private String input(String prompt) {
        while (true) {
            System.out.print(prompt + ": \u001b[4m");

            String input = sc.nextLine();

            System.out.println("\u001b[0m");

            if (input != "")
                return input;

            displayErr(new InputMismatchException("Cannot be an empty string"));
        }
    }
    
    /**
     * Double user input with text formatting
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

    private int dropdown(String... options) {
        while (true) {
            for (int i = 0; i < options.length; i++)
                System.out.println("[" + (i + 1) + "] " + options[i]);

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

    public int menu(String... options) {
        while (true) {
            for (int i = 0; i < options.length; i++)
                System.out.println("[" + (i + 1) + "] " + options[i]);

            System.out.println("[b] Back\n[x] Exit");

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
            } catch (NumberFormatException e) {}

            displayErr(new InputMismatchException("Invalid input. Try again."));

        }
    }

    public void displayErr(Exception e) {
        System.err.println("\n\u001b[41m" + e.getLocalizedMessage() + "\u001b[0m\n");
    }


    public String login() {
        return input("Login (Saves to a new user if not found)");
    }

    public CoffeeTruck addCoffeeTruck(boolean special) {
        String location = input("Location");
        List<StorageBin> storageBins = new ArrayList<>();

        Ingredient ingredient;

        for (int i = 0; i < 8; i++)
            while (true)
                try {
                    storageBins.add(new StorageBin(ingredient = Ingredient.values()[dropdown(Arrays.stream(Ingredient.values()).map(Enum::toString).toArray(String[]::new)) - 1], (ingredient == Ingredient.NONE) ? 0f : inputNumber("Quantity")));
                    break;
                } catch (ArithmeticException e) {
                    displayErr(e);
                }

        if (!special)
            return new CoffeeTruck(location, storageBins);

        // Special

        List<SpecialStorageBin> specialStorageBins = new ArrayList<>();

        SyrupIngredient syrupIngredient;

        for (int i = 0; i < 2; i++)
            while (true)
                try {
                    specialStorageBins.add(new SpecialStorageBin(syrupIngredient = SyrupIngredient.values()[dropdown(Arrays.stream(SyrupIngredient.values()).map(Enum::toString).toArray(String[]::new)) - 1], (syrupIngredient == SyrupIngredient.NONE) ? 0f : inputNumber("Quantity")));
                    break;
                } catch (ArithmeticException e) {
                    displayErr(e);
                }

        return new SpecialCoffeeTruck(location, storageBins, specialStorageBins);
    }
}
