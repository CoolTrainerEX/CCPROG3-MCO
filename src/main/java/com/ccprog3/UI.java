package com.ccprog3;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.ccprog3.coffee.Coffee;
import com.ccprog3.coffeeTruck.CoffeeTruck;
import com.ccprog3.coffeeTruck.StorageBin;

/**
 * UI for user display and input
 * 
 * @author Justin Ryan Uy
 */
public interface UI {
    /**
     * Radio display function
     * 
     * @param menu Map of menu options and their corresponding actions
     * @return false = back; true = exit
     */
    public boolean menu(Map<String, Supplier<Boolean>> menu);

    /**
     * Displays error message
     * 
     * @param e Exception
     */
    public void displayErr(Exception e);

    // UI

    /**
     * Logs the user in
     * 
     * @return Username
     */
    public String login();

    /**
     * Display for creating a new {@code CoffeeTruck}
     * 
     * @return The {@code CoffeeTruck} generated
     */
    public CoffeeTruck addCoffeeTruck();

    /**
     * Asks the user for {@code Coffee} to buy
     * 
     * @param special {@code CoffeeTruck} is special
     * @return {@code Coffee} to buy
     */
    public Coffee buyCoffee(boolean special);

    /**
     * Shows the process of making a {@code Coffee}
     * 
     * @param sale The {@code Coffee} to make and sell
     */
    public void makeCoffee(Map.Entry<Coffee, Money> sale);

    /**
     * Displays info of a {@code CoffeeTruck}
     * 
     * @param coffeeTruck {@code CoffeeTruck} to display
     * @param user        User to show prices
     */
    public void coffeeTruckInfo(CoffeeTruck coffeeTruck, UserSingleton user);

    /**
     * Display for choosing the available Coffee Trucks from the user
     * 
     * @param coffeeTrucks List of Coffee Trucks of the user
     * @return Index of the chosen {@code CoffeeTruck}
     */
    public int chooseCoffeeTruck(List<CoffeeTruck> coffeeTrucks);

    /**
     * Display for choosing the available Storage Bins from the {@code CoffeeTruck}
     * 
     * @param storageBins List of Storage Bins of the {@code CoffeeTruck}
     * @return Index of the chosen {@code StorageBin}
     */
    public int chooseStorageBin(List<StorageBin> storageBins);

    /**
     * Display to ask user for the quantity of {@code Ingredient} to add to a
     * {@code StorageBin}
     * 
     * @return Quantity to add
     */
    public double addStorageBinQuantity();

    /**
     * Display to ask the user for a new {@code StorageBin} to replace the selected
     * one
     * 
     * @param special {@code SpecialStorageBin} or regular
     * @return The new {@code StorageBin} to be replace with
     */
    public StorageBin setStorageBin(boolean special);

    /**
     * Display to ask the user for a new location
     * 
     * @return New location
     */
    public String setCoffeeTruckLocation();

    /**
     * Shows the dashboard summary of the Coffee Trucks
     * 
     * @param coffeeTrucks List of Coffee Trucks
     */
    public void dashboard(List<CoffeeTruck> coffeeTrucks);

    /**
     * Asks the user for new prices to set
     * 
     * @param <E>        Enum type
     * @param priceClass Type to set price to
     * @return Map of new prices
     */
    public <E extends Enum<E>> Map<E, Money> setPrices(Class<E> priceClass);
}
