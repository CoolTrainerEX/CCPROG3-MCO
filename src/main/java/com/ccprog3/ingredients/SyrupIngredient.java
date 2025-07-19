package com.ccprog3.ingredients;

/**
 * Syrup Ingredient types
 * 
 * @author Justin Ryan Uy
 */
public enum SyrupIngredient {
    /**
     * Hazelnut
     */
    HAZELNUT,

    /**
     * Chocolate
     */
    CHOCOLATE,

    /**
     * Almond
     */
    ALMOND,

    /**
     * Sweetener
     */
    SWEETENER,

    /**
     * Vanilla
     */
    VANILLA,

    /**
     * Empty
     */
    NONE;

    /**
     * Gets the unit of measurement for the {@code SyrupIngredient}
     * 
     * @return Unit of measurement
     */
    public Unit getUnit() {
        return Unit.FL_OZ;
    }
}
