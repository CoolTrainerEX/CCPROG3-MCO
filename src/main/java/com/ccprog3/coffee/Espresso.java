package com.ccprog3.coffee;

import java.util.Map;

import com.ccprog3.ingredients.Ingredient;

/**
 * Types of Espresso brews
 * 
 * @author Justin Ryan Uy
 */
public enum Espresso {
    /**
     * Standard brew 1:18
     */
    STANDARD,

    /**
     * Strong brew 1:15
     */
    STRONG,

    /**
     * Light brew 1:20
     */
    LIGHT,

    /**
     * Custom brew 1:?
     */
    CUSTOM;

    /**
     * Gives the raw Ingredients needed in percent
     * 
     * @return The Ingredients needed in percent as a Map of Ingredients.
     * @throws IllegalArgumentException Custom brew cannot have a ratio
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
