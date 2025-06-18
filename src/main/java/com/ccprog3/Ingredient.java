package com.ccprog3;

/**
 * Ingredient types
 * 
 * @author Justin Ryan Uy
 */
public enum Ingredient {
    /**
     * Small Cup 80 pcs
     * 
     * @author Justin Ryan Uy
     */
    SMALL_CUP,

    /**
     * Medium Cup 64 pcs
     * 
     * @author Justin Ryan Uy
     */
    MEDIUM_CUP,

    /**
     * Large Cup 40 pcs
     * 
     * @author Justin Ryan Uy
     */
    LARGE_CUP,

    /**
     * Coffee Beans 1008 g
     * 
     * @author Justin Ryan Uy
     */
    COFFEE_BEANS,

    /**
     * Milk 640 fl oz
     * 
     * @author Justin Ryan Uy
     */
    MILK,

    /**
     * Water 640 fl oz
     * 
     * @author Justin Ryan Uy
     */
    WATER,

    /**
     * Empty 640 fl oz
     * 
     * @author Justin Ryan Uy
     */
    NONE;

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
     * Gets the unit of measurement for the Ingredient
     * 
     * @return Unit of measurement
     * @author Justin Ryan Uy
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
     * Returns the max capacity a Storage Bin can hold of the Ingredient
     * 
     * @return Max capacity
     * @author Justin Ryan Uy
     */
    public double getMax() {
        switch (this) {
            case SMALL_CUP:
                return 80;

            case MEDIUM_CUP:
                return 64;

            case LARGE_CUP:
                return 40;

            case COFFEE_BEANS:
                return 1008;

            case MILK:
            case WATER:
            default:
                return 640;
        }
    }

    /**
     * Gives the volume of any Cup in fl oz
     * 
     * @return The volume in fl oz
     * @throws IllegalArgumentException Ingredient is not a Cup
     * @author Justin Ryan Uy
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
