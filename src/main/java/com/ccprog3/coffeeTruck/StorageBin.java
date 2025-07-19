package com.ccprog3.coffeeTruck;

import com.ccprog3.ingredients.Ingredient;

/**
 * Storage Bin class to store Ingredients
 * 
 * @author Justin Ryan Uy
 */
public class StorageBin {
    /**
     * {@code Ingredient} type stored
     */
    private final Ingredient ingredient;

    /**
     * Quantity of {@code Ingredient}
     */
    private double quantity;

    /**
     * {@code StorageBin} constructor
     * 
     * @param ingredient {@code Ingredient} type stored
     * @param quantity   Amount of the {@code Ingredient}
     * @throws ArithmeticException Quantity is negative or over the max capacity
     */
    public StorageBin(Ingredient ingredient, double quantity) throws ArithmeticException {
        this.ingredient = ingredient;

        checkQuantity(quantity);

        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ingredient + ": " + quantity + ingredient.getUnit();
    }

    /**
     * Gets the {@code Ingredient} stored
     * 
     * @return The {@code Ingredient} stored
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Gets the quantity
     * 
     * @return The quantity
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
     * Adds to the quantity of the {@code StorageBin}. Negative numbers can be used
     * to
     * subtract.
     * 
     * @param quantity The quantity to add
     * @throws ArithmeticException Quantity is negative or over the max capacity
     */
    public void addQuantity(double quantity) throws ArithmeticException {
        checkQuantity(quantity + this.quantity);

        this.quantity += quantity;
    }
}
