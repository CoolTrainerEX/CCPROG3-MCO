package com.ccprog3;

import java.util.Map;

/**
 * Types of Espresso brews
 * 
 * @author Justin Ryan Uy
 */
public enum Espresso {
    /**
     * Standard brew 1:18
     * 
     * @author Justin Ryan Uy
     */
    STANDARD,

    /**
     * Strong brew 1:15
     * 
     * @author Justin Ryan Uy
     */
    STRONG,

    /**
     * Light brew 1:20
     * 
     * @author Justin Ryan Uy
     */
    LIGHT,

    /**
     * Custom brew 1:? or No extra shot
     * 
     * @author Justin Ryan Uy
     */
    NONE;

    /**
     * Gives the raw Ingredients needed in percent
     * 
     * @return The Ingredients needed in percent as a Map of Ingredients.
     * @throws IllegalArgumentException Custom brew cannot have a ratio
     * @author Justin Ryan Uy
     */
    public Map<Ingredient, Double> getIngredients() throws IllegalArgumentException {
        switch (this) {
            case STANDARD:
                return Map.of(Ingredient.COFFEE_BEANS, (double) 1 / 19, Ingredient.WATER, (double) 18 / 19);

            case STRONG:
                return Map.of(Ingredient.COFFEE_BEANS, (double) 1 / 16, Ingredient.WATER, (double) 15 / 16);

            case LIGHT:
                return Map.of(Ingredient.COFFEE_BEANS, (double) 1 / 21, Ingredient.WATER, (double) 20 / 21);

            default:
                throw new IllegalArgumentException("Custom brew cannot have a ratio");
        }
    }
}
