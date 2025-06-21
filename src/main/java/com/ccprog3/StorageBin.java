package com.ccprog3;

/**
 * Storage Bin class to store Ingredients
 * 
 * @author Justin Ryan Uy
 */
public class StorageBin {
    /**
     * Ingredient type stored
     * 
     * @author Justin Ryan Uy
     */
    private final Ingredient ingredient;

    /**
     * Quantity of ingredient
     * 
     * @author Justin Ryan Uy
     */
    private double quantity;

    /**
     * Storage Bin constructor
     * 
     * @param ingredient Ingredient type stored
     * @param quantity   Amount of the ingredient
     * @throws ArithmeticException Quantity is negative or over the max capacity
     * @author Justin Ryan Uy
     */
    public StorageBin(Ingredient ingredient, double quantity) throws ArithmeticException {
        this.ingredient = ingredient;

        checkQuantity(quantity);

        this.quantity = quantity;
    }

    public String toString() {
        return ingredient + ": " + quantity + ingredient.getUnit();
    }

    /**
     * Gets the Ingredient stored
     * 
     * @return The Ingredient stored
     * @author Justin Ryan Uy
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Gets the quantity
     * 
     * @return The quantity
     * @author Justin Ryan Uy
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Throws an exception if the quantity is invalid
     * 
     * @param quantity The quantity to check
     * @throws ArithmeticException Quantity is negative or over the max capacity
     */
    private void checkQuantity(double quantity) throws ArithmeticException {
        if (quantity > ingredient.getMax())
            throw new ArithmeticException("Quantity greater than max capacity");
        if (quantity < 0)
            throw new ArithmeticException("Quantity cannot be negative");
    }

    /**
     * Adds to the quantity of the Storage Bin. Negative numbers can be used to
     * subtract.
     * 
     * @param quantity The quantity to add
     * @throws ArithmeticException Quantity is negative or over the max capacity
     * @author Justin Ryan Uy
     */
    public void addQuantity(double quantity) throws ArithmeticException {
        checkQuantity(quantity + this.quantity);

        this.quantity += quantity;
    }
}
