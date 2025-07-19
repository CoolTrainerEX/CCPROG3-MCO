package com.ccprog3.coffeeTruck;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.ccprog3.Money;
import com.ccprog3.UserSingleton;
import com.ccprog3.coffee.Coffee;
import com.ccprog3.coffee.CoffeeType;
import com.ccprog3.ingredients.Ingredient;

/**
 * Tests for {@code CoffeeTruck} class
 * 
 * @author Justin Ryan Uy
 */
public class CoffeeTruckTest {
    /**
     * Default constructor
     */
    public CoffeeTruckTest() {
    }

    /**
     * User to get prices
     */
    UserSingleton user = UserSingleton.getInstance();

    /**
     * Generates the Storage Bins with Ingredients to make the {@code Coffee}
     * 
     * @param coffee The {@code Coffee} to generate Ingredients for
     * @return The array of Storage Bins with Ingredients
     */
    private StorageBin[] generateStorageBins(Coffee coffee) {
        StorageBin[] storageBins = new StorageBin[8];

        for (int i = 0; i < storageBins.length; i++)
            storageBins[i] = new StorageBin(Ingredient.NONE, 0);

        int i = 0;

        for (Map.Entry<Ingredient, Double> ingredient : coffee.getAllIngredients().entrySet())
            storageBins[i++] = new StorageBin(ingredient.getKey(), ingredient.getValue());

        return storageBins;
    }

    /**
     * Tests {@code makeCoffee} when there is enough stock
     */
    @Test
    public void testMakeCoffeeValid() {
        Coffee coffee = new Coffee(CoffeeType.LATTE, Ingredient.MEDIUM_CUP);
        Map.Entry<Coffee, Money> result = (new CoffeeTruck("a", generateStorageBins(coffee))).makeCoffee(coffee, user);

        assertEquals(coffee, result.getKey());
        assertEquals(result.getValue().getAmount(), 24, 0.0001);
    }

    /**
     * Tests {@code makeCoffee} when there is not enough stock
     */
    @Test(expected = ArithmeticException.class)
    public void testMakeCoffeeInalid() {
        Coffee coffee = new Coffee(CoffeeType.LATTE, Ingredient.MEDIUM_CUP);
        CoffeeTruck coffeeTruck = new CoffeeTruck("a", generateStorageBins(coffee));

        coffeeTruck.emptyStorageBin(0);
        coffeeTruck.makeCoffee(coffee, user);
    }

    /**
     * Tests {@code makeCoffee} when there is no stock
     */
    @Test(expected = ArithmeticException.class)
    public void testMakeCoffeeInalidEmpty() {
        Coffee coffee = new Coffee(CoffeeType.LATTE, Ingredient.MEDIUM_CUP);

        StorageBin[] storageBins = new StorageBin[8];

        for (int i = 0; i < storageBins.length; i++)
            storageBins[i] = new StorageBin(Ingredient.NONE, 0);

        (new CoffeeTruck("a", storageBins)).makeCoffee(coffee, user);
    }
}
