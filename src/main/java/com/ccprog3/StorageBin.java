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
     * Unit of measurement
     * @author Justin Ryan Uy
     */
    protected final Unit unit;

    /**
     * Max capacity
     * @author Justin Ryan Uy
     */
    protected final double max;

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
        switch (ingredient) {
            case SMALL_CUP:
                unit = Unit.PIECES;
                max = 80;
                break;

            case MEDIUM_CUP:
                unit = Unit.PIECES;
                max = 64;
                break;

            case LARGE_CUP:
                unit = Unit.PIECES;
                max = 40;
                break;

            case COFFEE_BEANS:
                unit = Unit.GRAMS;
                max = 1008;
                break;

            case MILK:
            case WATER:
            default:
                unit = Unit.FL_OZ;
                max = 640;
                break;
        }

        if (quantity > max)
            throw new ArithmeticException("Quantity greater than max capacity");
        if (quantity < 0)
            throw new ArithmeticException("Quantity cannot be negative");
        
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
}
