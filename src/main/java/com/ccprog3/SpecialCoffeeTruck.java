package com.ccprog3;

import java.util.List;

/**
 * Special Coffee Truck with special features
 * @author Justin Ryan Uy
 */
public class SpecialCoffeeTruck extends CoffeeTruck {
    
    /**
     * Special Storage Bins to contain Syrup Ingredients
     * @author Justin Ryan Uy
     */
    private final List<SpecialStorageBin> specialStorageBins;
    
     /**
      * Coffee Truck constructor
      * @param location Coffee Truck location
      * @param storageBins The Storage Bins to be placed in the Coffee Truck (Max 8)
      * @param specialStorageBins The Special Storage Bins to be placed in the Coffee Truck (Max 2)
      * @throws ArrayIndexOutOfBoundsException Too much Storage Bins set
      * @author Justin Ryan Uy
      */
    public SpecialCoffeeTruck(String location, List<StorageBin> storageBins, List<SpecialStorageBin> specialStorageBins) throws ArrayIndexOutOfBoundsException {
        super(location, storageBins);
        
        if (specialStorageBins.size() > 2)
            throw new ArrayIndexOutOfBoundsException("Too much Special Storage Bins set");

        this.specialStorageBins = specialStorageBins;
    }
}
