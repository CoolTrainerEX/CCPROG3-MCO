package com.ccprog3;

import java.util.ArrayList;
import java.util.List;

/**
 * Coffee Truck class
 * 
 * @author Justin Ryan Uy
 */
public class CoffeeTruck {
    /**
     * Truck location
     * 
     * @author Justin Ryan Uy
     */
    protected String location;

    /**
     * Storage Bins to store Ingredients
     * 
     * @author Justin Ryan Uy
     */
    protected final StorageBin[] storageBins = new StorageBin[8];

    /**
     * List of transactions
     * 
     * @author Justin Ryan Uy
     */
    protected final List<Coffee> sales = new ArrayList<>();

    /**
     * Coffee Truck constructor
     * 
     * @param location    Coffee Truck location
     * @param storageBins The Storage Bins to be placed in the Coffee Truck (Max 8)
     * @throws IndexOutOfBoundsException Too much Storage Bins set
     * @author Justin Ryan Uy
     */
    public CoffeeTruck(String location, StorageBin[] storageBins) throws IndexOutOfBoundsException {
        try {
            System.arraycopy(storageBins, 0, this.storageBins, 0, storageBins.length);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Too much Storage Bins set");
        }

        this.location = location;
    }

    /**
     * Gets the Truck location
     * 
     * @return Truck location
     * @author Justin Ryan Uy
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the Storage Bin array
     * 
     * @return Storage Bin array
     * @author Justin Ryan Uy
     */
    public StorageBin[] getStorageBins() {
        return storageBins;
    }

    /**
     * Gets the Coffee Truck sales
     * 
     * @return Coffee Truck Sales
     * @author Justin Ryan Uy
     */
    public Coffee[] getSales() {
        return sales.toArray(new Coffee[0]);
    }

    /**
     * Sets the Truck location
     * 
     * @param location Truck location
     * @author Justin Ryan Uy
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Adds to the quantity of the Storage Bin. Negative numbers can be used to
     * subtract.
     * 
     * @param quantity The quantity to add
     * @param index    Index of the Storage Bin to add
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     * @throws ArithmeticException            Quantity is negative or over the max
     *                                        capacity
     * @author Justin Ryan Uy
     */
    public void addStorageBinQuantity(double quantity, int index)
            throws ArrayIndexOutOfBoundsException, ArithmeticException {
        storageBins[index].addQuantity(quantity);
    }

    /**
     * Sets a new Ingredient into a Storage Bin
     * 
     * @param storageBin new Storage Bin to be replaced with
     * @param index      Index of the Storage Bin to replace
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     * @author Justin Ryan Uy
     */
    public void setStorageBin(StorageBin storageBin, int index) throws ArrayIndexOutOfBoundsException {
        storageBins[index] = storageBin;
    }

    /**
     * Empties a Storage Bin
     * 
     * @param index Index of the Storage Bin to empty
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     * @author Justin Ryan Uy
     */
    public void emptyStorageBin(int index) throws ArrayIndexOutOfBoundsException {
        storageBins[index] = new StorageBin(Ingredient.NONE, 0);
    }
}
