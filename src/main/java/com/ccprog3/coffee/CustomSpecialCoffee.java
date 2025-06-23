package com.ccprog3.coffee;

import java.util.Map;

import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;

/**
 * Special Coffee with a custom brew ratio
 * 
 * @author Justin Ryan Uy
 */
public class CustomSpecialCoffee extends SpecialCoffee {
    /**
     * Custom brew ratio
     * 
     * @author Justin Ryan Uy
     */
    private final int ratio;

    /**
     * Constructor for Special Coffee with custom brew ratio
     * 
     * @param type   Type of Coffee
     * @param cup    Cup size
     * @param ratio  Custom brew ratio of water to coffee (ex. 2 = 1:2)
     * @param syrups Syrup addons
     * @param shots  Extra shots
     * @throws IllegalArgumentException Ingredient is not a cup
     * @author Justin Ryan Uy
     */
    public CustomSpecialCoffee(CoffeeType type, Ingredient cup, int ratio, SyrupIngredient[] syrups, Espresso[] shots)
            throws IllegalArgumentException {
        super(type, cup, Espresso.CUSTOM, syrups, shots);
        this.ratio = ratio;
    }

    public Map<Ingredient, Double> getEspressoIngredients() {
        return Map.of(Ingredient.COFFEE_BEANS, (double) 1, Ingredient.WATER, (double) ratio);
    }
}
