package com.ccprog3;

/**
 * Special Coffee Truck with special features
 * @author Justin Ryan Uy
 */
public class SpecialCoffeeTruck extends CoffeeTruck {
    
    /**
     * Special Storage Bins to contain Syrup Ingredients
     * @author Justin Ryan Uy
     */
    private final SpecialStorageBin[] specialStorageBins = new SpecialStorageBin[2];
    
     /**
      * Coffee Truck constructor
      * @param location Coffee Truck location
      * @param storageBins The Storage Bins to be placed in the Coffee Truck (Max 8)
      * @param specialStorageBins The Special Storage Bins to be placed in the Coffee Truck (Max 2)
      * @throws ArrayIndexOutOfBoundsException Too much Storage Bins set
      * @author Justin Ryan Uy
      */
    public SpecialCoffeeTruck(Location location, StorageBin[] storageBins, SpecialStorageBin[] specialStorageBins) throws ArrayIndexOutOfBoundsException {
        super(location, storageBins);
        
        if (specialStorageBins.length > this.specialStorageBins.length)
            throw new ArrayIndexOutOfBoundsException("Too much Special Storage Bins set");

        System.arraycopy(specialStorageBins, 0, this.specialStorageBins, 0, specialStorageBins.length);
    }
}
