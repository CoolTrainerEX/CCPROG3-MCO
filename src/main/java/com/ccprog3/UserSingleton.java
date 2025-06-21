package com.ccprog3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    private final Map<CoffeeType, Float> coffeePrices = new HashMap<>();
    private final Map<Espresso, Float> espressoPrices = new HashMap<>();
    private final Map<SyrupIngredient, Float> syrupPrices = new HashMap<>();

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
     * Gets the list of Cofee Trucks
     * 
     * @return The array of Cofee Trucks
     * @author Justin Ryan Uy
     */
    public CoffeeTruck[] getCoffeeTrucks() {
        return coffeeTrucks.toArray(new CoffeeTruck[0]);
    }

    public void close() {
        // TODO Write file
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
