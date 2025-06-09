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
     * Special Storage Bin class to contain Syrup Ingredients
     * @author Justin Ryan Uy
     */
    private class SpecialStorageBin extends StorageBin {

    }
}
