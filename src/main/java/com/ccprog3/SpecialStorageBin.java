package com.ccprog3;

/**
 * Special Storage Bin class to contain Syrup Ingredients
 * 
 * @author Justin Ryan Uy
 */
public class SpecialStorageBin extends StorageBin {
    /**
     * Syrup Ingredient type stored
     * 
     * @author Justin Ryan Uy
     */
    private final SyrupIngredient syrupIngredient;

    /**
     * Special Storage Bin constructor
     * 
     * @param syrupIngredient Syrup Ingredient type stored
     * @param quantity        Amount of the Ingredient
     * @throws ArithmeticException Quantity is negative or over the max capacity
     * @author Justin Ryan Uy
     */
    public SpecialStorageBin(SyrupIngredient syrupIngredient, double quantity) throws ArithmeticException {
        super(Ingredient.NONE, quantity); // Default capacity is 640 fl oz

        this.syrupIngredient = syrupIngredient;
    }

    public String toString() {
        return syrupIngredient + ": " + quantity + super.getIngredient().getUnit();
    }

    /**
     * Gets the Syrup Ingredient stored
     * 
     * @return The Syrup Ingredient stored
     * @author Justin Ryan Uy
     */
    public SyrupIngredient getSyrupIngredient() {
        return syrupIngredient;
    }

}
