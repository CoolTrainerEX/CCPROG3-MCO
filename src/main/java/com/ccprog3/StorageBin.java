package com.ccprog3;

/**
 * Storage Bin class to store Ingredients
 * @author Justin Ryan Uy
 */
public class StorageBin {
    /**
     * Ingredient type stored
     * @author Justin Ryan Uy
     */
    private final Ingredient ingredient;

    /**
     * Quantity of ingredient
     * @author Justin Ryan Uy
     */
    protected double quantity;

    /**
     * Storage Bin constructor
     * @param ingredient Ingredient type stored
     * @param quantity Amount of the ingredient
     * @throws ArithmeticException Quantity is negative or over the max capacity
     * @author Justin Ryan Uy
     */
    public StorageBin(Ingredient ingredient, double quantity) throws ArithmeticException {
        if (quantity > ingredient.getMax())
            throw new ArithmeticException("Quantity greater than max capacity");
        if (quantity < 0)
            throw new ArithmeticException("Quantity cannot be negative");
        
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
     
    /**
     * Gets the Ingredient stored
     * @return The Ingredient stored
     * @author Justin Ryan Uy
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Gets the quantity
     * @return The quantity
     * @author Justin Ryan Uy
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Adds to the quantity of the Storage Bin. Negative numbers can be used to subtract.
     * @param quantity The quantity to add
     * @throws ArithmeticException Quantity is negative or over the max capacity
     * @author Justin Ryan Uy
     */
    public void addQuantity(int quantity) throws ArithmeticException {
        if (quantity + this.quantity > ingredient.getMax())
            throw new ArithmeticException("Quantity greater than max capacity");
        if (quantity + this.quantity < 0)
            throw new ArithmeticException("Quantity cannot be negative");

        this.quantity += quantity;
    }
}
