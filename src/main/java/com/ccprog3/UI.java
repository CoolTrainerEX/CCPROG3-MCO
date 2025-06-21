package com.ccprog3;

import java.util.List;

/**
 * UI for user display and input
 * 
 * @author Justin Ryan Uy
 */
public interface UI {
    /**
     * Radio display function
     * 
     * @param options Text options to display
     * @return Option number; 0 = back; -1 = exit
     * @author Justin Ryan Uy
     */
    public int menu(String... options);

    /**
     * Displays error message
     * 
     * @param e Exception
     * @author Justin Ryan Uy
     */
    public void displayErr(Exception e);

    // UI

    /**
     * Logs the user in
     * 
     * @return Username
     * @author Justin Ryan Uy
     */
    public String login();

    /**
     * Display for creating a new Coffee Truck
     * 
     * @return The Coffee Truck generated
     * @author Justin Ryan Uy
     */
    public CoffeeTruck addCoffeeTruck();

    /**
     * Displays info of a Coffee Truck
     * 
     * @param coffeeTruck Coffee Truck to display
     * @author Justin Ryan Uy
     */
    public void coffeeTruckInfo(CoffeeTruck coffeeTruck);

    /**
     * Display for choosing the available Coffee Trucks from the user
     * 
     * @param coffeeTrucks List of Coffee Trucks of the user
     * @return Index of the chosen Coffee Truck
     * @author Justin Ryan Uy
     */
    public int chooseCoffeeTruck(List<CoffeeTruck> coffeeTrucks);

    /**
     * Display for choosing the available Storage Bins from the Coffee Truck
     * 
     * @param storageBins List of Storage Bins of the Coffee Truck
     * @return Index of the chosen Storage Bin
     * @author Justin Ryan Uy
     */
    public int chooseStorageBin(List<StorageBin> storageBins);

    /**
     * Display to ask user for the quantity of Ingredient to add to a Storage Bin
     * 
     * @return Quantity to add
     * @author Justin Ryan Uy
     */
    public double addStorageBinQuantity();

    /**
     * Display to ask the user for a new Storage Bin to replace the selected one
     * 
     * @param special Special Storage Bin or regular
     * @return The new Storage Bin to be replace with
     */
    public StorageBin setStorageBin(boolean special);

    /**
     * Display to ask the user for a new location
     * 
     * @return New location
     */
    public String setCoffeeTruckLocation();
}
