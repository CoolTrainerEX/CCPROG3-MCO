package com.ccprog3.coffee;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.ccprog3.ingredients.Ingredient;

/**
 * Class to store Coffee details
 * 
 * @author Justin Ryan Uy
 */
public class Coffee {
    /**
     * Type of {@code Coffee} product
     */
    protected final CoffeeType type;

    /**
     * Cup size of the {@code Coffee}
     */
    protected final Ingredient cup;

    /**
     * {@code Coffee} class constructor
     * 
     * @param type Type of {@code Coffee}
     * @param cup  Cup size
     * @throws IllegalArgumentException {@code Ingredient} is not a Cup
     */
    public Coffee(CoffeeType type, Ingredient cup) throws IllegalArgumentException {
        cup.getCupVolume(); // Check if Ingredient is Cup

        this.type = type;
        this.cup = cup;
    }

    @Override
    public String toString() {
        return cup + " " + type;
    }

    /**
     * Gets the {@code CoffeeType}
     * 
     * @return The {@code CoffeeType}
     */
    public CoffeeType getType() {
        return type;
    }

    /**
     * Gets the Cup size
     * 
     * @return Cup size of the {@code Coffee}
     */
    public Ingredient getCup() {
        return cup;
    }

    /**
     * Gets the Ingredients required to make the {@code Coffee} without the
     * {@code Espresso}
     * 
     * @return Map of Ingredients and quantities
     */
    public Map<Ingredient, Double> getIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(type.getIngredients());

        ingredients.replaceAll((_, quantity) -> quantity * cup.getCupVolume());
        ingredients.put(cup, (double) 1);

        return Collections.unmodifiableMap(ingredients);
    }

    /**
     * Gets the Ingredients required to make the {@code Espresso}
     * 
     * @return Map of Ingredients and quantities
     */
    public Map<Ingredient, Double> getEspressoIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(Espresso.STANDARD.getIngredients());
        double espressoQuantity = cup.getCupVolume()
                * (1 - type.getIngredients().values().stream().mapToDouble(Double::doubleValue).sum());

        ingredients.replaceAll((_, quantity) -> quantity * espressoQuantity);

        return Collections.unmodifiableMap(ingredients);
    }

    /**
     * Gets the total Ingredients required to make the {@code Coffee}
     * 
     * @return Map of Ingredients and quantities
     */
    public Map<Ingredient, Double> getAllIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(getIngredients());

        for (Map.Entry<Ingredient, Double> espressoIngredient : getEspressoIngredients().entrySet())
            ingredients.merge(espressoIngredient.getKey(), espressoIngredient.getValue(), Double::sum);

        return Collections.unmodifiableMap(ingredients);
    }
}
