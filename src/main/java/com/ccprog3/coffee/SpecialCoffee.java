package com.ccprog3.coffee;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;

/**
 * Special Coffee from {@code SpecialCoffeeTruck}
 * 
 * @author Justin Ryan Uy
 */
public class SpecialCoffee extends Coffee {
    /**
     * {@code Espresso} brew
     */
    private final Espresso espresso;

    /**
     * Syrup addons
     */
    private final List<SyrupIngredient> syrups;

    /**
     * Extra shots
     */
    private final List<Espresso> shots;

    /**
     * {@code SpecialCoffee} constructor
     * 
     * @param type     Type of {@code Coffee}
     * @param cup      Cup size
     * @param espresso {@code Espresso} brew
     * @param syrups   Syrup addons
     * @param shots    Extra shots
     * @throws IllegalArgumentException {@code Ingredient} is not a Cup; Syrup
     *                                  cannot be
     *                                  NONE; Shot cannot be CUSTOM
     */
    public SpecialCoffee(CoffeeType type, Ingredient cup, Espresso espresso, SyrupIngredient[] syrups, Espresso[] shots)
            throws IllegalArgumentException {
        super(type, cup);

        if ((this.syrups = List.of(syrups)).contains(SyrupIngredient.NONE))
            throw new IllegalArgumentException("Syrup cannot be NONE");

        if ((this.shots = List.of(shots)).contains(Espresso.CUSTOM))
            throw new IllegalArgumentException("Shot cannot be CUSTOM");

        this.espresso = espresso;
    }

    /**
     * Gets the {@code Espresso} brew
     * 
     * @return The {@code Espresso} brew
     */
    public Espresso getEspresso() {
        return espresso;
    }

    /**
     * Gets the List of Syrup addons
     * 
     * @return The List of Syrup addons
     */
    public List<SyrupIngredient> getSyrups() {
        return syrups;
    }

    /**
     * Gets the List of extra shots
     * 
     * @return The List of extra shots
     */
    public List<Espresso> getShots() {
        return shots;
    }

    @Override
    public String toString() {
        return cup + " " + espresso + " " + type;
    }

    @Override
    public Map<Ingredient, Double> getEspressoIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(espresso.getIngredients());
        double espressoQuantity = cup.getCupVolume()
                * (1 - type.getIngredients().values().stream().mapToDouble(Double::doubleValue).sum());

        ingredients.replaceAll((_, quantity) -> quantity * espressoQuantity);

        return Collections.unmodifiableMap(ingredients);
    }

    /**
     * Gets the Ingredients required to make the Syrup
     * 
     * @return Map of Syrup Ingredients and quantities
     */
    public Map<SyrupIngredient, Double> getSyrupIngredients() {
        Map<SyrupIngredient, Double> syrupIngredients = new HashMap<>();

        for (SyrupIngredient syrupIngredient : syrups)
            syrupIngredients.merge(syrupIngredient, (double) 1, Double::sum);

        return Collections.unmodifiableMap(syrupIngredients);
    }

    /**
     * Gets the Ingredients required to make the extra shots
     * 
     * @return Map of Ingredients and quantities
     */
    public Map<Ingredient, Double> getShotIngredients() {
        Map<Ingredient, Double> shotIngredients = new HashMap<>();

        for (Espresso shot : shots)
            for (Map.Entry<Ingredient, Double> shotIngredient : shot.getIngredients().entrySet())
                shotIngredients.merge(shotIngredient.getKey(), shotIngredient.getValue(), Double::sum);

        return Collections.unmodifiableMap(shotIngredients);
    }

    @Override
    public Map<Ingredient, Double> getAllIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(super.getAllIngredients());

        for (Map.Entry<Ingredient, Double> shotIngredient : getShotIngredients().entrySet())
            ingredients.merge(shotIngredient.getKey(), shotIngredient.getValue(), Double::sum);

        return Collections.unmodifiableMap(ingredients);
    }
}
