package com.ccprog3.coffeeTruck;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccprog3.Money;
import com.ccprog3.UserSingleton;
import com.ccprog3.coffee.Coffee;
import com.ccprog3.coffee.Espresso;
import com.ccprog3.ingredients.Ingredient;

/**
 * Coffee Truck class
 * 
 * @author Justin Ryan Uy
 */
public class CoffeeTruck {
    /**
     * Truck location
     */
    private String location;

    /**
     * Storage Bins to store Ingredients
     */
    protected final StorageBin[] storageBins = new StorageBin[8];

    /**
     * List of transactions
     */
    private final Map<Coffee, Money> sales = new HashMap<>();

    /**
     * {@code CoffeeTruck} constructor
     * 
     * @param location    {@code CoffeeTruck} location
     * @param storageBins The Storage Bins to be placed in the {@code CoffeeTruck}
     *                    (Size 8)
     * @throws NullPointerException      Null in Storage Bins
     * @throws IndexOutOfBoundsException Storage Bins must be eight
     */
    public CoffeeTruck(String location, StorageBin[] storageBins)
            throws NullPointerException, IndexOutOfBoundsException {
        if (storageBins.length != this.storageBins.length)
            throw new IndexOutOfBoundsException("Storage Bins must be eight");

        System.arraycopy(storageBins, 0, this.storageBins, 0, storageBins.length);

        this.location = location;
    }

    @Override
    public String toString() {
        return "Regular Coffee Truck" + ": " + location;
    }

    /**
     * Gets the Truck location
     * 
     * @return Truck location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the {@code StorageBin} List
     * 
     * @return {@code StorageBin} List
     */
    public List<StorageBin> getStorageBins() {
        return List.of(storageBins);
    }

    /**
     * Gets the {@code CoffeeTruck} sales
     * 
     * @return {@code CoffeeTruck} Sales
     */
    public Map<Coffee, Money> getSales() {
        return Collections.unmodifiableMap(sales);
    }

    /**
     * Sets the Truck location
     * 
     * @param location Truck location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets a new {@code Ingredient} into a {@code StorageBin}
     * 
     * @param storageBin new {@code StorageBin} to be replaced with
     * @param index      Index of the {@code StorageBin} to replace
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     */
    public void setStorageBin(StorageBin storageBin, int index) throws ArrayIndexOutOfBoundsException {
        storageBins[index] = storageBin;
    }

    /**
     * Empties a {@code StorageBin}
     * 
     * @param index Index of the {@code StorageBin} to empty
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     */
    public void emptyStorageBin(int index) throws ArrayIndexOutOfBoundsException {
        storageBins[index] = new StorageBin(Ingredient.NONE, 0);
    }

    /**
     * Checks and subtracts the required stocks to make the {@code Coffee}
     * 
     * @param coffee The {@code Coffee} to make
     * @param user   User to get prices
     * @return The generated {@code Coffee} sale
     * @throws ArithmeticException Not enough stock
     */
    public Map.Entry<Coffee, Money> makeCoffee(Coffee coffee, UserSingleton user) throws ArithmeticException {
        Map<Ingredient, Double> stock = new HashMap<>();

        for (StorageBin storageBin : storageBins)
            stock.merge(storageBin.getIngredient(), storageBin.getQuantity(), Double::sum);

        for (Map.Entry<Ingredient, Double> ingredient : coffee.getAllIngredients().entrySet())
            if (!stock.containsKey(ingredient.getKey()) || stock.get(ingredient.getKey()) < ingredient.getValue())
                throw new ArithmeticException("Not enough stock");

        double remaining;

        for (Map.Entry<Ingredient, Double> ingredient : coffee.getAllIngredients().entrySet()) {
            remaining = ingredient.getValue();

            for (StorageBin storageBin : storageBins)
                if (storageBin.getIngredient() == ingredient.getKey())
                    try {
                        storageBin.addQuantity(-remaining);
                    } catch (ArithmeticException e) {
                        remaining -= storageBin.getQuantity();
                        storageBin.addQuantity(-storageBin.getQuantity());
                    }
        }

        sales.put(coffee, calculatePrice(coffee, user));

        return Map.entry(coffee, sales.get(coffee));
    }

    /**
     * Calculates the price of the {@code Coffee}
     * 
     * @param coffee The {@code Coffee} to price
     * @param user   User to get prices
     * @return Price of the {@code Coffee}
     */
    protected Money calculatePrice(Coffee coffee, UserSingleton user) {
        return new Money(
                (float) coffee.getCup().getCupVolume() * (user.getCoffeePrices().get(coffee.getType()).getAmount()
                        + user.getEspressoPrices().get(Espresso.STANDARD).getAmount()));
    }
}
