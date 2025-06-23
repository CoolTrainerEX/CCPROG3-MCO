package com.ccprog3.coffee;

import java.util.Map;

import com.ccprog3.ingredients.Ingredient;

/**
 * Types of Coffee products
 * 
 * @author Justin Ryan Uy
 */
public enum CoffeeType {
    /**
     * Cafe Americano 1:2 Water
     * 
     * @author Justin Ryan Uy
     */
    CAFE_AMERICANO,

    /**
     * Latte 1:4 Milk
     * 
     * @author Justin Ryan Uy
     */
    LATTE,

    /**
     * Cappuccino 1:2 Milk
     * 
     * @author Justin Ryan Uy
     */
    CAPPUCCINO;

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
     *         Remaining percentage is Espresso.
     * @author Justin Ryan Uy
     */
    public Map<Ingredient, Double> getIngredients() {
        switch (this) {
            case CAFE_AMERICANO:
                return Map.of(Ingredient.WATER, (double) 2 / 3);

            case LATTE:
                return Map.of(Ingredient.MILK, (double) 4 / 5);

            case CAPPUCCINO:
            default:
                return Map.of(Ingredient.MILK, (double) 2 / 3);
        }
    }
}
