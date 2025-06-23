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
     * 
     * @author Justin Ryan Uy
     */
    private String location;

    /**
     * Storage Bins to store Ingredients
     * 
     * @author Justin Ryan Uy
     */
    protected final StorageBin[] storageBins = new StorageBin[8];

    /**
     * List of transactions
     * 
     * @author Justin Ryan Uy
     */
    private final Map<Coffee, Money> sales = new HashMap<>();

    /**
     * Coffee Truck constructor
     * 
     * @param location    Coffee Truck location
     * @param storageBins The Storage Bins to be placed in the Coffee Truck (Size 8)
     * @throws IndexOutOfBoundsException Too much Storage Bins set
     * @author Justin Ryan Uy
     */
    public CoffeeTruck(String location, StorageBin[] storageBins) throws IndexOutOfBoundsException {
        try {
            System.arraycopy(storageBins, 0, this.storageBins, 0, storageBins.length);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Too much Storage Bins set");
        }

        this.location = location;
    }

    public String toString() {
        return "Regular Coffee Truck" + ": " + location;
    }

    /**
     * Gets the Truck location
     * 
     * @return Truck location
     * @author Justin Ryan Uy
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the Storage Bin List
     * 
     * @return Storage Bin List
     * @author Justin Ryan Uy
     */
    public List<StorageBin> getStorageBins() {
        return List.of(storageBins);
    }

    /**
     * Gets the Coffee Truck sales
     * 
     * @return Coffee Truck Sales
     * @author Justin Ryan Uy
     */
    public Map<Coffee, Money> getSales() {
        return Collections.unmodifiableMap(sales);
    }

    /**
     * Sets the Truck location
     * 
     * @param location Truck location
     * @author Justin Ryan Uy
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets a new Ingredient into a Storage Bin
     * 
     * @param storageBin new Storage Bin to be replaced with
     * @param index      Index of the Storage Bin to replace
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     * @author Justin Ryan Uy
     */
    public void setStorageBin(StorageBin storageBin, int index) throws ArrayIndexOutOfBoundsException {
        storageBins[index] = storageBin;
    }

    /**
     * Empties a Storage Bin
     * 
     * @param index Index of the Storage Bin to empty
     * @throws ArrayIndexOutOfBoundsException Index out of bounds
     * @author Justin Ryan Uy
     */
    public void emptyStorageBin(int index) throws ArrayIndexOutOfBoundsException {
        storageBins[index] = new StorageBin(Ingredient.NONE, 0);
    }

    /**
     * Checks and subtracts the required stocks to make the Coffee
     * 
     * @param coffee The Coffee to make
     * @param user   User to get prices
     * @return The generated Coffee sale
     * @throws ArithmeticException Not enough stock
     * @author Justin Ryan Uy
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
     * Calculates the price of the Coffee
     * 
     * @param coffee The Coffee to price
     * @param user   User to get prices
     * @return Price of the Coffee
     * @author Justin Ryan Uy
     */
    protected Money calculatePrice(Coffee coffee, UserSingleton user) {
        return new Money(
                (float) coffee.getCup().getCupVolume() * (user.getCoffeePrices().get(coffee.getType()).getAmount()
                        + user.getEspressoPrices().get(Espresso.STANDARD).getAmount()));
    }
}
