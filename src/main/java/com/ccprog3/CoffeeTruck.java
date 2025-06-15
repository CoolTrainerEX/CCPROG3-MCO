package com.ccprog3;

/**
 * Coffee Truck class
 * @author Justin Ryan Uy
 */
public class CoffeeTruck {
    /**
     * Truck Location
     * @author Justin Ryan Uy
     */
    protected final Location location;

    /**
     * Storage Bins to store Ingredients
     * @author Justin Ryan Uy
     */
    protected final StorageBin[] storageBins = new StorageBin[8];

     /**
      * Coffee Truck constructor
      * @param location Coffee Truck location
      * @param storageBins The Storage Bins to be placed in the Coffee Truck (Max 8)
      * @throws ArrayIndexOutOfBoundsException Too much Storage Bins set
      * @author Justin Ryan Uy
      */
    public CoffeeTruck(Location location, StorageBin[] storageBins) throws ArrayIndexOutOfBoundsException {
        if (storageBins.length > this.storageBins.length)
            throw new ArrayIndexOutOfBoundsException("Too much Storage Bins set");
        
        this.location = location;
        System.arraycopy(storageBins, 0, this.storageBins, 0, storageBins.length);
    }

    /**
     * Gets the Truck location (Can be used to set new coordinates)
     * @return Truck location
     * @author Justin Ryan Uy
     */
    public Location getLocation() {
        return location;
    }
}
