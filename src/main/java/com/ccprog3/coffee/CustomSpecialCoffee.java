package com.ccprog3.coffee;

import java.util.Map;

import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;

/**
 * {@code SpecialCoffee} with a custom brew ratio
 * 
 * @author Justin Ryan Uy
 */
public class CustomSpecialCoffee extends SpecialCoffee {
    /**
     * Custom brew ratio
     */
    private final int ratio;

    /**
     * Constructor for {@code SpecialCoffee} with custom brew ratio
     * 
     * @param type   Type of {@code Coffee}
     * @param cup    Cup size
     * @param ratio  Custom brew ratio of water to coffee beans (ex. 2 = 1:2)
     * @param syrups Syrup addons
     * @param shots  Extra shots
     * @throws IllegalArgumentException {@code Ingredient} is not a Cup
     */
    public CustomSpecialCoffee(CoffeeType type, Ingredient cup, int ratio, SyrupIngredient[] syrups, Espresso[] shots)
            throws IllegalArgumentException {
        super(type, cup, Espresso.CUSTOM, syrups, shots);
        this.ratio = ratio;
    }

    @Override
    public Map<Ingredient, Double> getEspressoIngredients() {
        return Map.of(Ingredient.COFFEE_BEANS, (double) 1, Ingredient.WATER, (double) ratio);
    }
}
