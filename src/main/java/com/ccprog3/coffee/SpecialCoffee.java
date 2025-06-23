package com.ccprog3.coffee;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;

/**
 * Special Coffee from Special Coffee Truck
 * 
 * @author Justin Ryan Uy
 */
public class SpecialCoffee extends Coffee {
    /**
     * Espresso brew
     * 
     * @author Justin Ryan Uy
     */
    private final Espresso espresso;

    /**
     * Syrup addons
     * 
     * @author Justin Ryan Uy
     */
    private final List<SyrupIngredient> syrups;

    /**
     * Extra shots
     * 
     * @author Justin Ryan Uy
     */
    private final List<Espresso> shots;

    /**
     * Special Coffee constructor
     * 
     * @param type     Type of Coffee
     * @param cup      Cup size
     * @param espresso Espresso brew
     * @param syrups   Syrup addons
     * @param shots    Extra shots
     * @throws IllegalArgumentException Ingredient is not a cup; Syrup cannot be
     *                                  NONE; Shot cannot be CUSTOM
     * @author Justin Ryan Uy
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
     * Gets the Espresso brew
     * 
     * @return The Espresso brew
     * @author Justin Ryan Uy
     */
    public Espresso getEspresso() {
        return espresso;
    }

    /**
     * Gets the List of Syrup addons
     * 
     * @return The List of Syrup addons
     * @author Justin Ryan Uy
     */
    public List<SyrupIngredient> getSyrups() {
        return syrups;
    }

    /**
     * Gets the List of extra shots
     * 
     * @return The List of extra shots
     * @author Justin Ryan Uy
     */
    public List<Espresso> getShots() {
        return shots;
    }

    public String toString() {
        return cup + " " + espresso + " " + type;
    }

    public Map<Ingredient, Double> getEspressoIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(espresso.getIngredients());
        double espressoQuantity = cup.getCupVolume()
                * (1 - type.getIngredients().values().stream().mapToDouble(Double::doubleValue).sum());

        ingredients.replaceAll((ingredient, quantity) -> quantity * espressoQuantity);

        return Collections.unmodifiableMap(ingredients);
    }

    /**
     * Gets the ingredients required to make the Syrup
     * 
     * @return Map of Syrup Ingredients and quantities
     * @author Justin Ryan Uy
     */
    public Map<SyrupIngredient, Double> getSyrupIngredients() {
        Map<SyrupIngredient, Double> syrupIngredients = new HashMap<>();

        for (SyrupIngredient syrupIngredient : syrups)
            syrupIngredients.merge(syrupIngredient, (double) 1, Double::sum);

        return Collections.unmodifiableMap(syrupIngredients);
    }

    /**
     * Gets the ingredients required to make the extra shots
     * 
     * @return Map of Ingredients and quantities
     * @author Justin Ryan Uy
     */
    public Map<Ingredient, Double> getShotIngredients() {
        Map<Ingredient, Double> shotIngredients = new HashMap<>();

        for (Espresso shot : shots)
            for (Map.Entry<Ingredient, Double> shotIngredient : shot.getIngredients().entrySet())
                shotIngredients.merge(shotIngredient.getKey(), shotIngredient.getValue(), Double::sum);

        return Collections.unmodifiableMap(shotIngredients);
    }

    public Map<Ingredient, Double> getAllIngredients() {
        Map<Ingredient, Double> ingredients = new HashMap<>(super.getAllIngredients());

        for (Map.Entry<Ingredient, Double> shotIngredient : getShotIngredients().entrySet())
            ingredients.merge(shotIngredient.getKey(), shotIngredient.getValue(), Double::sum);

        return Collections.unmodifiableMap(ingredients);
    }
}
