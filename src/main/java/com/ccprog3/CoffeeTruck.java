package com.ccprog3;

import java.util.List;

/**
 * Coffee Truck class
 * @author Justin Ryan Uy
 */
public class CoffeeTruck {
    /**
     * Truck Location
     * @author Justin Ryan Uy
     */
    protected final String location;

    /**
     * Storage Bins to store Ingredients
     * @author Justin Ryan Uy
     */
    protected final List<StorageBin> storageBins;

     /**
      * Coffee Truck constructor
      * @param location Coffee Truck location
      * @param storageBins The Storage Bins to be placed in the Coffee Truck (Max 8)
      * @throws ArrayIndexOutOfBoundsException Too much Storage Bins set
      * @author Justin Ryan Uy
      */
    public CoffeeTruck(String location, List<StorageBin> storageBins) throws ArrayIndexOutOfBoundsException {
        if (storageBins.size() > 8)
            throw new ArrayIndexOutOfBoundsException("Too much Storage Bins set");
        
        this.location = location;
        this.storageBins = storageBins;
    }

    /**
     * Gets the Truck location (Can be used to set new coordinates)
     * @return Truck location
     * @author Justin Ryan Uy
     */
    public String getLocation() {
        return location;
    }
}
