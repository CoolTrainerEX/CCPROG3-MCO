package com.ccprog3;

/**
 * Types of Coffee products
 * @author Justin Ryan Uy
 */
public enum CoffeeType {
    /**
     * Cafe Americano 1:2 Water
     * @author Justin Ryan Uy
     */
    CAFE_AMERICANO,

    /**
     * Latte 1:4 Milk
     * @author Justin Ryan Uy
     */
    LATTE,

    /**
     * Cappuccino 1:2 Milk
     * @author Justin Ryan Uy
     */
    CAPPUCCINO;

    /**
     * Gives the raw Ingredients needed in percent
     * @param espresso The Espresso brew used
     * @return The Ingredients needed in percent as an array of Storage Bins
     * @author Justin Ryan Uy
     */
    public StorageBin[] getIngredients(Espresso espresso) {
        switch (this) {
            case CAFE_AMERICANO:
                return new StorageBin[] { new StorageBin(Ingredient.COFFEE_BEANS, (1 - espresso.getRatio()) * 1 / 3), new StorageBin(Ingredient.WATER, espresso.getRatio() * 1 / 3), new StorageBin(Ingredient.WATER, 2 / 3) };

            case LATTE:
                return new StorageBin[] { new StorageBin(Ingredient.COFFEE_BEANS, (1 - espresso.getRatio()) * 1 / 5), new StorageBin(Ingredient.WATER, espresso.getRatio() * 1 / 5), new StorageBin(Ingredient.MILK, 4 / 5) };

            case CAPPUCCINO:
            default:
                return new StorageBin[] { new StorageBin(Ingredient.COFFEE_BEANS, (1 - espresso.getRatio()) * 1 / 3), new StorageBin(Ingredient.WATER, espresso.getRatio() * 1 / 3), new StorageBin(Ingredient.MILK, 2 / 3) };
        }
    }
}
