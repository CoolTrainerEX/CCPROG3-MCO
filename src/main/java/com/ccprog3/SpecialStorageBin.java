package com.ccprog3;

/**
 * Special Storage Bin class to contain Syrup Ingredients
 * @author Justin Ryan Uy
 */
public class SpecialStorageBin extends StorageBin {
    private final SyrupIngredient syrupIngredient;

    /**
     * Special Storage Bin constructor
     * @param syrupIngredient Syrup Ingredient type stored
     * @param quantity Amount of the Ingredient
     * @throws ArithmeticException Quantity is negative or over the max capacity
     * @author Justin Ryan Uy
     */
    public SpecialStorageBin(SyrupIngredient syrupIngredient, double quantity) throws ArithmeticException {
        super(Ingredient.WATER, quantity); // Water has similar properties
        
        this.syrupIngredient = syrupIngredient;
    }
}
