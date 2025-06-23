package com.ccprog3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * User class for the runtime instance
 * 
 * @author Justin Ryan Uy
 */
public class UserSingleton implements AutoCloseable {
    /**
     * Singleton instance of the User
     * 
     * @author Justin Ryan Uy
     */
    private static final UserSingleton instance = new UserSingleton();

    /**
     * Username to be used for reading and writing to files
     * 
     * @author Justin Ryan Uy
     */
    private String username;

    /**
     * List of Coffee Trucks made by the User
     * 
     * @author Justin Ryan Uy
     */
    private final List<CoffeeTruck> coffeeTrucks = new ArrayList<>();

    /**
     * Prices of Coffee Types per fluid ounce
     * 
     * @author Justin Ryan Uy
     */
    private final Map<CoffeeType, Money> coffeePrices = new HashMap<>();

    /**
     * Prices of Espresso shots per fluid ounce
     * 
     * @author Justin Ryan Uy
     */
    private final Map<Espresso, Money> espressoPrices = new HashMap<>();
    /**
     * Prices of Syrup Ingredients per fluid ounce
     * 
     * @author Justin Ryan Uy
     */
    private final Map<SyrupIngredient, Money> syrupPrices = new HashMap<>();

    /**
     * Initializes the default prices
     * 
     * @author Justin Ryan Uy
     */
    private UserSingleton() {
        for (CoffeeType coffeeType : CoffeeType.values())
            coffeePrices.put(coffeeType, new Money(1));

        for (Espresso espresso : Espresso.values())
            espressoPrices.put(espresso, new Money(1));

        for (SyrupIngredient syrupIngredient : SyrupIngredient.values())
            syrupPrices.put(syrupIngredient, new Money(10));

        syrupPrices.remove(SyrupIngredient.NONE);
    }

    /**
     * Gets the User singleton instance
     * 
     * @return The instance
     * @author Justin Ryan Uy
     */
    public static UserSingleton getInstance() {
        return instance;
    }

    /**
     * Gets the username
     * 
     * @return Username
     * @author Justin Ryan Uy
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the List of Coffee Trucks
     * 
     * @return The List of Coffee Trucks
     * @author Justin Ryan Uy
     */
    public List<CoffeeTruck> getCoffeeTrucks() {
        return Collections.unmodifiableList(coffeeTrucks);
    }

    /**
     * Gets the prices of Coffee Types per fluid ounce
     * 
     * @return Map of Coffee Types and their prices
     * @author Justin Ryan Uy
     */
    public Map<CoffeeType, Money> getCoffeePrices() {
        return Collections.unmodifiableMap(coffeePrices);
    }

    /**
     * Gets the prices of Espresso shots per fluid ounce
     * 
     * @return Map of Espressos and their prices
     * @author Justin Ryan Uy
     */
    public Map<Espresso, Money> getEspressoPrices() {
        return Collections.unmodifiableMap(espressoPrices);
    }

    /**
     * Gets the prices of Syrup Ingredients per fluid ounce
     * 
     * @return Map of Syrup Ingredients and their prices
     * @author Justin Ryan Uy
     */
    public Map<SyrupIngredient, Money> getSyrupPrices() {
        return Collections.unmodifiableMap(syrupPrices);
    }

    /**
     * Sets the prices of the given Coffee Types per fluid ounce
     * 
     * @param coffeePrices Map of Coffee Types and their prices
     * @author Justin Ryan Uy
     */
    public void setCoffeePrices(Map<CoffeeType, Money> coffeePrices) {
        this.coffeePrices.putAll(coffeePrices);
    }

    /**
     * Sets the prices of the given Espresso shots per fluid ounce
     * 
     * @param espressoPrices Map of Espressos and their prices
     * @author Justin Ryan Uy
     */
    public void setEspressoPrices(Map<Espresso, Money> espressoPrices) {
        this.espressoPrices.putAll(espressoPrices);
    }

    /**
     * Sets the prices of the given Syrup Ingredients per fluid ounce
     * 
     * @param syrupPrices Map of Syrup Ingredients and their prices
     * @throws IllegalArgumentException Cannot set a price for None
     * @author Justin Ryan Uy
     */
    public void setSyrupPrices(Map<SyrupIngredient, Money> syrupPrices) throws IllegalArgumentException {
        if (syrupPrices.containsKey(SyrupIngredient.NONE))
            throw new IllegalArgumentException("Cannot set a price for None");

        this.syrupPrices.putAll(syrupPrices);
    }

    public void close() {
        // TODO Write file
    }

    /**
     * Trows an Exception if the location is already occupied
     * 
     * @param location The location to check
     * @throws IllegalArgumentException Location is already occupied
     * @author Justin Ryan Uy
     */
    private void checkAvailableLocation(String location) throws IllegalArgumentException {
        for (CoffeeTruck coffeeTruck : coffeeTrucks)
            if (location.equals(coffeeTruck.getLocation()))
                throw new IllegalArgumentException("Location is already occupied");
    }

    /**
     * Logs the user in (opens save file)
     * 
     * @param username Username to log in with
     * @throws FileNotFoundException User not found
     * @author Justin Ryan Uy
     */
    public void login(String username) throws FileNotFoundException {
        this.username = username;

        try (Scanner filesc = new Scanner(new File(username))) {
            // TODO read file
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("User not found. Will save to new user upon exit.");
        }
    }

    /**
     * Adds a Coffee Truck
     * 
     * @param coffeeTruck The Coffee Truck to add
     * @throws IllegalArgumentException Location is already occupied
     * @author Justin Ryan Uy
     */
    public void addCoffeeTruck(CoffeeTruck coffeeTruck) throws IllegalArgumentException {
        checkAvailableLocation(coffeeTruck.getLocation());
        coffeeTrucks.add(coffeeTruck);
    }

    /**
     * Sets the new Coffee Truck location
     * 
     * @param location New location
     * @param index    Coffee Truck index
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     * @throws IllegalArgumentException       Location is already occupied
     * @author Justin Ryan Uy
     */
    public void setCoffeeTruckLocation(String location, int index)
            throws IllegalArgumentException, IndexOutOfBoundsException {
        checkAvailableLocation(location);
        coffeeTrucks.get(index).setLocation(location);
    }
}
