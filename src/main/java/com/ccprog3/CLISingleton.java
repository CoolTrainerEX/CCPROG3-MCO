package com.ccprog3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ccprog3.coffee.Coffee;
import com.ccprog3.coffee.CoffeeType;
import com.ccprog3.coffee.CustomSpecialCoffee;
import com.ccprog3.coffee.Espresso;
import com.ccprog3.coffee.SpecialCoffee;
import com.ccprog3.coffeeTruck.CoffeeTruck;
import com.ccprog3.coffeeTruck.SpecialCoffeeTruck;
import com.ccprog3.coffeeTruck.SpecialStorageBin;
import com.ccprog3.coffeeTruck.StorageBin;
import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;
import com.ccprog3.ingredients.Unit;

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

    public Coffee buyCoffee(boolean special) {
        CoffeeType type = CoffeeType
                .values()[dropdown(Arrays.stream(CoffeeType.values()).map(Enum::toString).toArray(String[]::new)) - 1];
        Ingredient[] cups = Arrays.stream(Ingredient.values()).filter((ingredient) -> {
            try {
                ingredient.getCupVolume(); // Check if ingredient is a cup
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }).toArray(Ingredient[]::new);
        Ingredient cup = cups[dropdown(Arrays.stream(cups).map(Enum::toString).toArray(String[]::new)) - 1];

        if (!special)
            return new Coffee(type, cup);

        // Special

        System.out.println("\u001b[1;31mEspresso\u001b[0m\n");

        Espresso espresso = Espresso
                .values()[dropdown(Arrays.stream(Espresso.values()).map(Enum::toString).toArray(String[]::new)) - 1];
        int ratio = 0;

        if (espresso == Espresso.CUSTOM) {
            double ratioDouble;

            while ((ratioDouble = inputNumber("Custom espresso water ratio")) != (int) ratioDouble)
                displayErr(new InputMismatchException("Input is not an integer. Try again."));

            ratio = (int) ratioDouble;
        }

        List<SyrupIngredient> syrups = new ArrayList<>();
        SyrupIngredient syrupIngredient;

        System.out.println("\u001b[1;31mAdd Syrups\u001b[0m\n");

        while ((syrupIngredient = SyrupIngredient
                .values()[dropdown(Arrays.stream(SyrupIngredient.values()).map(Enum::toString).toArray(String[]::new))
                        - 1]) != SyrupIngredient.NONE)
            syrups.add(syrupIngredient);

        List<Espresso> shots = new ArrayList<>();
        Espresso shot;

        System.out.println("\u001b[1;31mAdd extra shots\u001b[0m\n");

        while ((shot = Espresso.values()[dropdown(Arrays.stream(Espresso.values()).map(Enum::toString)
                .map((string) -> string.equals("CUSTOM") ? "NONE" : string).toArray(String[]::new))
                - 1]) != Espresso.CUSTOM)
            shots.add(shot);

        if (espresso == Espresso.CUSTOM)
            return new CustomSpecialCoffee(type, cup, ratio, syrups.toArray(SyrupIngredient[]::new),
                    shots.toArray(Espresso[]::new));

        return new SpecialCoffee(type, cup, espresso, syrups.toArray(SyrupIngredient[]::new),
                shots.toArray(Espresso[]::new));
    }

    public void makeCoffee(Coffee coffee) {

    }

    public void coffeeTruckInfo(CoffeeTruck coffeeTruck, UserSingleton user) {
        System.out.println("\u001b[1;44m" + coffeeTruck + "\u001b[0;32m\n");

        for (StorageBin storageBin : coffeeTruck.getStorageBins())
            System.out.println(storageBin);

        for (Map.Entry<Coffee, Money> sale : coffeeTruck.getSales().entrySet()) {
            System.out.println("\n\u001b[1;3;31m" + sale.getKey() + ": " + sale.getValue() + "\u001b[0;3;31m");

            for (Map.Entry<Ingredient, Double> ingredient : sale.getKey().getAllIngredients().entrySet())
                System.out.println("\t" + ingredient.getKey() + ": "
                        + (ingredient.getKey().getUnit() == Unit.GRAMS ? Unit.flozToG(ingredient.getValue())
                                : ingredient.getValue())
                        + ingredient.getKey().getUnit());

            if (sale instanceof SpecialCoffee)
                for (Map.Entry<SyrupIngredient, Double> syrupIngredient : ((SpecialCoffee) sale.getKey())
                        .getSyrupIngredients().entrySet())
                    System.out.println("\t" + syrupIngredient.getKey() + ": " + syrupIngredient.getValue()
                            + syrupIngredient.getKey().getUnit());
        }

        boolean special = coffeeTruck instanceof SpecialCoffeeTruck;

        System.out.println("\n\u001b[1;34mCoffee Prices per " + Unit.FL_OZ + "\u001b[0;34m");

        for (Map.Entry<CoffeeType, Money> coffeePrice : user.getCoffeePrices().entrySet())
            System.out.println("\t" + coffeePrice.getKey() + ": "
                    + (special ? coffeePrice.getValue()
                            : new Money(coffeePrice.getValue().getAmount()
                                    + user.getEspressoPrices().get(Espresso.STANDARD).getAmount())));

        if (special) {
            System.out.println("\n\u001b[1;34mEspresso Prices per " + Unit.FL_OZ + "\u001b[0;34m");

            for (Map.Entry<Espresso, Money> espressoPrice : user.getEspressoPrices().entrySet())
                System.out.println("\t" + espressoPrice.getKey() + ": " + espressoPrice.getValue());

            System.out.println("\n\u001b[1;34mSyrup Prices\u001b[0;34m");

            for (Map.Entry<SyrupIngredient, Money> syrupPrice : user.getSyrupPrices().entrySet())
                System.out.println("\t" + syrupPrice.getKey() + ": " + syrupPrice.getValue());
        }

        System.out.println("\u001b[0m");
    }

    public int chooseCoffeeTruck(List<CoffeeTruck> coffeeTrucks) {
        return dropdown(coffeeTrucks.stream().map(Object::toString).toArray(String[]::new)) - 1;
    }

    public int chooseStorageBin(List<StorageBin> storageBins) {
        return dropdown(storageBins.stream().map(Object::toString).toArray(String[]::new)) - 1;
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

    public void dashboard(List<CoffeeTruck> coffeeTrucks) {
        Map<Ingredient, Double> ingredients = new HashMap<>();
        Map<SyrupIngredient, Double> syrups = new HashMap<>();
        Map<Coffee, Money> sales = new HashMap<>();

        System.out.print("\u001b[1;34m");

        for (CoffeeTruck coffeeTruck : coffeeTrucks.stream()
                .filter((coffeeTruck) -> !(coffeeTruck instanceof SpecialCoffeeTruck)).toList()) {
            System.out.println(coffeeTruck);

            sales.putAll(coffeeTruck.getSales());

            for (StorageBin storageBin : coffeeTruck.getStorageBins())
                ingredients.merge(storageBin.getIngredient(), storageBin.getQuantity(), Double::sum);
        }

        for (CoffeeTruck specialCoffeeTruck : coffeeTrucks.stream()
                .filter((coffeeTruck) -> coffeeTruck instanceof SpecialCoffeeTruck).toList()) {
            System.out.println(specialCoffeeTruck);

            sales.putAll(specialCoffeeTruck.getSales());

            for (StorageBin storageBin : specialCoffeeTruck.getStorageBins())
                if (storageBin instanceof SpecialStorageBin)
                    syrups.merge(((SpecialStorageBin) storageBin).getSyrupIngredient(), storageBin.getQuantity(),
                            Double::sum);
                else
                    ingredients.merge(storageBin.getIngredient(), storageBin.getQuantity(), Double::sum);
        }

        ingredients.remove(Ingredient.NONE);
        syrups.remove(SyrupIngredient.NONE);

        System.out.println("\u001b[0;32m");

        for (Map.Entry<Ingredient, Double> ingredient : ingredients.entrySet())
            System.out.println(ingredient.getKey() + ": " + ingredient.getValue() + ingredient.getKey().getUnit());

        for (Map.Entry<SyrupIngredient, Double> syrupIngredient : syrups.entrySet())
            System.out.println(
                    syrupIngredient.getKey() + ": " + syrupIngredient.getValue() + syrupIngredient.getKey().getUnit());

        System.out.println("\u001b[3;31m");

        for (Map.Entry<Coffee, Money> sale : sales.entrySet())
            System.out.println(sale.getKey() + ": " + sale.getValue());

        System.out.println("\u001b[0m");
    }

    @Override
    public <E extends Enum<E>> Map<E, Money> setPrices(Class<E> priceClass) {
        Map<E, Money> prices = new HashMap<>();

        for (E entry : Arrays.stream(priceClass.getEnumConstants()).filter((entry) -> !entry.toString().equals("NONE"))
                .toList())
            prices.put(entry, new Money((float) inputNumber("Price for " + entry + " per " + Unit.FL_OZ)));

        return Collections.unmodifiableMap(prices);
    }
}
