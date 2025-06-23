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
     * Type of Coffee product
     * 
     * @author Justin Ryan Uy
     */
    protected final CoffeeType type;

    /**
     * Cup size of the Coffee
     * 
     * @author Justin Ryan Uy
     */
    protected final Ingredient cup;

    /**
     * Coffee class constructor
     * 
     * @param type Type of Coffee
     * @param cup  Cup size
     * @throws IllegalArgumentException Ingredient is not a cup
     * @author Justin Ryan Uy
     */
    public Coffee(CoffeeType type, Ingredient cup) throws IllegalArgumentException {
        cup.getCupVolume(); // Check if Ingredient is Cup

        this.type = type;
        this.cup = cup;
    }

    public String toString() {
        return cup + " " + type;
    }

    /**
     * Gets the Coffee type
     * 
     * @return The Coffee type
     * @author Justin Ryan Uy
     */
    public CoffeeType getType() {
        return type;
    }

    /**
     * Gets the Cup size
     * 
     * @return Cup size of the Coffee
     * @author Justin Ryan Uy
     */
    public Ingredient getCup() {
        return cup;
    }

    /**
     * Gets the ingredients required to make the Coffee without the Espresso
     * 
     * @return Map of Ingredients and quantities
     * @author Justin Ryan Uy
     */
    public Map<Ingredient, Double> getIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(type.getIngredients());

        ingredients.replaceAll((ingredient, quantity) -> quantity * cup.getCupVolume());

        return Collections.unmodifiableMap(ingredients);
    }

    /**
     * Gets the ingredients required to make the Espresso
     * 
     * @return Map of Ingredients and quantities
     * @author Justin Ryan Uy
     */
    public Map<Ingredient, Double> getEspressoIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(Espresso.STANDARD.getIngredients());
        double espressoQuantity = cup.getCupVolume()
                * (1 - type.getIngredients().values().stream().mapToDouble(Double::doubleValue).sum());

        ingredients.replaceAll((ingredient, quantity) -> quantity * espressoQuantity);

        return Collections.unmodifiableMap(ingredients);
    }

    /**
     * Gets the total ingredients required to make the Coffee
     * 
     * @return Map of Ingredients and quantities
     * @author Justin Ryan Uy
     */
    public Map<Ingredient, Double> getAllIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(getIngredients());

        for (Map.Entry<Ingredient, Double> espressoIngredient : getEspressoIngredients().entrySet())
            ingredients.merge(espressoIngredient.getKey(), espressoIngredient.getValue(), Double::sum);

        return Collections.unmodifiableMap(ingredients);
    }
}
