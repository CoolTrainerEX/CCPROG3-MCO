package com.ccprog3;

/**
 * UI for user display and input
 * @author Justin Ryan Uy
 */
public interface UI {
    /**
     * Radio display function
     * @param options Text options to display
     * @return Option number; 0 = back; -1 = exit
     * @author Justin Ryan Uy
     */
    public int menu(String... options);

    /**
     * Displays error message
     * @param e Exception
     * @author Justin Ryan Uy
     */
    public void displayErr(Exception e);

    // UI

    /**
     * Logs the user in
     * @return Username
     * @author Justin Ryan Uy
     */
    public String login();

    /**
     * Display for creating a new Coffee Truck
     * @param special Whether it is Special Coffee Truck or Regular Coffee Truck
     * @return The Coffee Truck generated
     * @author Justin Ryan Uy
     */
    public CoffeeTruck addCoffeeTruck(boolean special);

    public int chooseCoffeeTruck(CoffeeTruck[] coffeeTrucks);

    public int chooseStorageBin(StorageBin[] storageBins);

    public double storageBinAddQuantity();

    public StorageBin setStorageBin(boolean special);

    public String setCoffeeTruckLocation();
}
