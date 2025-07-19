package com.ccprog3.coffee;

import java.util.Map;

import com.ccprog3.ingredients.Ingredient;

/**
 * Types of {@code Coffee} products
 * 
 * @author Justin Ryan Uy
 */
public enum CoffeeType {
    /**
     * Cafe Americano 1:2 Water
     */
    CAFE_AMERICANO(Map.of(Ingredient.WATER, (double) 2 / 3)),

    /**
     * Latte 1:4 Milk
     */
    LATTE(Map.of(Ingredient.MILK, (double) 4 / 5)),

    /**
     * Cappuccino 1:2 Milk
     */
    CAPPUCCINO(Map.of(Ingredient.MILK, (double) 2 / 3));

    /**
     * The Ingredients needed in percent as a Map of Ingredients. Remaining
     * percentage is {@code Espresso}
     */
    private Map<Ingredient, Double> ingredients;

    /**
     * {@code CoffeeType} constructor
     * 
     * @param ingredients The Ingredients needed in percent
     */
    private CoffeeType(Map<Ingredient, Double> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        switch (this) {
            case CAFE_AMERICANO:
                return "Cafe Americano";

            case LATTE:
                return "Latte";

            case CAPPUCCINO:
            default:
                return "Cappuccino";
        }
    }

    /**
     * Gives the raw Ingredients needed in percent
     * 
     * @return The Ingredients needed in percent as a Map of Ingredients.
     *         Remaining percentage is {@code Espresso}.
     */
    public Map<Ingredient, Double> getIngredients() {
        return ingredients;
    }
}
