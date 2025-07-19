package com.ccprog3.coffeeTruck;

import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;

/**
 * Special Storage Bin class to contain Syrup Ingredients
 * 
 * @author Justin Ryan Uy
 */
public class SpecialStorageBin extends StorageBin {
    /**
     * {@code SyrupIngredient} type stored
     */
    private final SyrupIngredient syrupIngredient;

    /**
     * {@code SpecialStorageBin} constructor
     * 
     * @param syrupIngredient {@code SyrupIngredient} type stored
     * @param quantity        Amount of the {@code Ingredient}
     * @throws ArithmeticException Quantity is negative or over the max capacity
     */
    public SpecialStorageBin(SyrupIngredient syrupIngredient, double quantity) throws ArithmeticException {
        super(Ingredient.NONE, quantity); // Default capacity is 640 fl oz

        this.syrupIngredient = syrupIngredient;
    }

    @Override
    public String toString() {
        return syrupIngredient + ": " + super.getQuantity() + syrupIngredient.getUnit();
    }

    /**
     * Gets the {@code SyrupIngredient} stored
     * 
     * @return The {@code SyrupIngredient} stored
     */
    public SyrupIngredient getSyrupIngredient() {
        return syrupIngredient;
    }

}
