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
    STANDARD(Map.of(Ingredient.COFFEE_BEANS, (double) 1 / 19, Ingredient.WATER, (double) 18 / 19)),

    /**
     * Strong brew 1:15
     */
    STRONG(Map.of(Ingredient.COFFEE_BEANS, (double) 1 / 16, Ingredient.WATER, (double) 15 / 16)),

    /**
     * Light brew 1:20
     */
    LIGHT(Map.of(Ingredient.COFFEE_BEANS, (double) 1 / 21, Ingredient.WATER, (double) 20 / 21)),

    /**
     * Custom brew 1:?
     */
    CUSTOM(null);

    /**
     * The Ingredients needed in percent as a Map of Ingredients
     */
    private final Map<Ingredient, Double> ingredients;

    /**
     * {@code Espresso} constructor
     * 
     * @param ingredients The Ingredients needed in percent
     */
    private Espresso(Map<Ingredient, Double> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gives the raw Ingredients needed in percent
     * 
     * @return The Ingredients needed in percent as a Map of Ingredients
     * @throws IllegalArgumentException Custom brew cannot have a ratio
     */
    public Map<Ingredient, Double> getIngredients() throws IllegalArgumentException {
        if (ingredients == null)
            throw new IllegalArgumentException("Custom brew cannot have a ratio");

        return ingredients;
    }
}
