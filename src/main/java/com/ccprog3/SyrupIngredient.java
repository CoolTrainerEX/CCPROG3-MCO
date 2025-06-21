package com.ccprog3;

/**
 * Syrup Ingredient types
 * 
 * @author Justin Ryan Uy
 */
public enum SyrupIngredient {
    /**
     * Hazelnut
     * 
     * @author Justin Ryan Uy
     */
    HAZELNUT,

    /**
     * Chocolate
     * 
     * @author Justin Ryan Uy
     */
    CHOCOLATE,

    /**
     * Almond
     * 
     * @author Justin Ryan Uy
     */
    ALMOND,

    /**
     * Sweetener
     * 
     * @author Justin Ryan Uy
     */
    SWEETENER,

    /**
     * Vanilla
     * 
     * @author Justin Ryan Uy
     */
    VANILLA,

    /**
     * Empty
     * 
     * @author Justin Ryan Uy
     */
    NONE;

    /**
     * Gets the unit of measurement for the Syrup Ingredient
     * 
     * @return Unit of measurement
     * @author Justin Ryan Uy
     */
    public Unit getUnit() {
        return Unit.FL_OZ;
    }
}
