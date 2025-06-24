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
 * Tests for CoffeeTruck class
 * 
 * @author Justin Ryan Uy
 */
public class CoffeeTruckTest {
    /**
     * User to get prices
     * 
     * @author Justin Ryan Uy
     */
    UserSingleton user = UserSingleton.getInstance();

    /**
     * Generates the Storage Bins with Ingredients to make the Coffee
     * 
     * @param coffee The coffee to generate Ingredients for
     * @return The array of Storage Bins with Ingredients
     * @author Justin Ryan Uy
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
     * Tests makeCoffee when there is enough stock
     * 
     * @author Justin Ryan Uy
     */
    @Test
    public void testMakeCoffeeValid() {
        Coffee coffee = new Coffee(CoffeeType.LATTE, Ingredient.MEDIUM_CUP);

        CoffeeTruck coffeeTruck = new CoffeeTruck("a", generateStorageBins(coffee));

        assertEquals(coffeeTruck.makeCoffee(coffee, user), Map.entry(coffee, new Money(24)));
    }

    /**
     * Tests makeCoffee when there is not enough stock
     * 
     * @author Justin Ryan Uy
     */
    @Test(expected = ArithmeticException.class)
    public void testMakeCoffeeInalid() {
        Coffee coffee = new Coffee(CoffeeType.LATTE, Ingredient.MEDIUM_CUP);

        CoffeeTruck coffeeTruck = new CoffeeTruck("a", generateStorageBins(coffee));

        coffeeTruck.emptyStorageBin(0);
        coffeeTruck.makeCoffee(coffee, user);
    }
}
