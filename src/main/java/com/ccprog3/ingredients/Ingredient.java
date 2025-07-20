package com.ccprog3.ingredients;

/**
 * Ingredient types
 * 
 * @author Justin Ryan Uy
 */
public enum Ingredient {
    /**
     * Small Cup 80 pcs
     */
    SMALL_CUP(80),

    /**
     * Medium Cup 64 pcs
     */
    MEDIUM_CUP(64),

    /**
     * Large Cup 40 pcs
     */
    LARGE_CUP(40),

    /**
     * Coffee Beans 1008 g
     */
    COFFEE_BEANS(1008),

    /**
     * Milk 640 fl oz
     */
    MILK(640),

    /**
     * Water 640 fl oz
     */
    WATER(640),

    /**
     * Empty or {@code SyrupIngredient} 640 fl oz
     */
    NONE(640);

    /**
     * Max capacity
     */
    private final double max;

    /**
     * {@code Ingredient} constructor
     * 
     * @param max Max capacity
     */
    private Ingredient(double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        switch (this) {
            case SMALL_CUP:
                return "Small Cup";

            case MEDIUM_CUP:
                return "Medium Cup";

            case LARGE_CUP:
                return "Large Cup";

            case COFFEE_BEANS:
                return "Coffee Beans";

            case MILK:
                return "Milk";

            case WATER:
                return "Water";

            default:
                return "None";
        }
    }

    /**
     * Gets the unit of measurement for the {@code Ingredient}
     * 
     * @return Unit of measurement
     */
    public Unit getUnit() {
        switch (this) {
            case SMALL_CUP:
            case MEDIUM_CUP:
            case LARGE_CUP:
                return Unit.PIECES;

            case COFFEE_BEANS:
                return Unit.GRAMS;

            case MILK:
            case WATER:
            default:
                return Unit.FL_OZ;
        }
    }

    /**
     * Returns the max capacity a {@code StorageBin} can hold of the
     * {@code Ingredient}
     * 
     * @return Max capacity
     */
    public double getMax() {
        return max;
    }

    /**
     * Gives the volume of any Cup in fl oz
     * 
     * @return The volume in fl oz
     * @throws IllegalArgumentException {@code Ingredient} is not a Cup
     */
    public double getCupVolume() throws IllegalArgumentException {
        switch (this) {
            case SMALL_CUP:
                return 8;

            case MEDIUM_CUP:
                return 12;

            case LARGE_CUP:
                return 16;

            default:
                throw new IllegalArgumentException("Ingredient is not a Cup");
        }
    }
}
