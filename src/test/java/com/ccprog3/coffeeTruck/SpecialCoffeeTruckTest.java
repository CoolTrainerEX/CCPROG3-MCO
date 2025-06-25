package com.ccprog3.coffeeTruck;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.ccprog3.Money;
import com.ccprog3.UserSingleton;
import com.ccprog3.coffee.Coffee;
import com.ccprog3.coffee.CoffeeType;
import com.ccprog3.coffee.Espresso;
import com.ccprog3.coffee.SpecialCoffee;
import com.ccprog3.ingredients.Ingredient;
import com.ccprog3.ingredients.SyrupIngredient;

/**
 * Tests for SpecialCoffeeTruck class
 * 
 * @author Justin Ryan Uy
 */
public class SpecialCoffeeTruckTest {
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
     * Generates the Special Storage Bins with Syrup Ingredients to make the Coffee
     * 
     * @param coffee The coffee to generate Syrup Ingredients for
     * @return The array of Special Storage Bins with Syrup Ingredients
     * @author Justin Ryan Uy
     */
    private SpecialStorageBin[] generateSpecialStorageBins(SpecialCoffee coffee) {
        SpecialStorageBin[] specialStorageBins = new SpecialStorageBin[2];

        for (int i = 0; i < specialStorageBins.length; i++)
            specialStorageBins[i] = new SpecialStorageBin(SyrupIngredient.NONE, 0);

        int i = 0;

        for (Map.Entry<SyrupIngredient, Double> syrupIngredient : coffee.getSyrupIngredients().entrySet())
            specialStorageBins[i++] = new SpecialStorageBin(syrupIngredient.getKey(), syrupIngredient.getValue());

        return specialStorageBins;
    }

    /**
     * Tests makeCoffee when there is enough stock
     * 
     * @author Justin Ryan Uy
     */
    @Test
    public void testMakeCoffeeValid() {
        SpecialCoffee coffee = new SpecialCoffee(CoffeeType.LATTE, Ingredient.MEDIUM_CUP, Espresso.LIGHT,
                new SyrupIngredient[] { SyrupIngredient.HAZELNUT }, new Espresso[] { Espresso.STRONG });

        Map.Entry<Coffee, Money> result = (new SpecialCoffeeTruck("a", generateStorageBins(coffee),
                generateSpecialStorageBins(coffee))).makeCoffee(coffee,
                        user);

        assertEquals(coffee, result.getKey());
        assertEquals(result.getValue().getAmount(), 35, 0.0001);
    }

    /**
     * Tests makeCoffee when there is not enough stock
     * 
     * @author Justin Ryan Uy
     */
    @Test(expected = ArithmeticException.class)
    public void testMakeCoffeeInvalid() {
        SpecialCoffee coffee = new SpecialCoffee(CoffeeType.LATTE, Ingredient.MEDIUM_CUP, Espresso.LIGHT,
                new SyrupIngredient[] { SyrupIngredient.HAZELNUT }, new Espresso[] { Espresso.STRONG });

        SpecialCoffeeTruck coffeeTruck = new SpecialCoffeeTruck("a", generateStorageBins(coffee), generateSpecialStorageBins(coffee));

        coffeeTruck.emptyStorageBin(8);
        coffeeTruck.makeCoffee(coffee, user);
    }

    /**
     * Tests makeCoffee when there is no stock
     * 
     * @author Justin Ryan Uy
     */
    @Test(expected = ArithmeticException.class)
    public void testMakeCoffeeInvalidEmpty() {
        SpecialCoffee coffee = new SpecialCoffee(CoffeeType.LATTE, Ingredient.MEDIUM_CUP, Espresso.LIGHT,
                new SyrupIngredient[] { SyrupIngredient.HAZELNUT }, new Espresso[] { Espresso.STRONG });

        StorageBin[] storageBins = new StorageBin[8];
        SpecialStorageBin[] specialStorageBins = new SpecialStorageBin[2];

        for (int i = 0; i < storageBins.length; i++)
            storageBins[i] = new StorageBin(Ingredient.NONE, 0);

        for (int i = 0; i < specialStorageBins.length; i++)
            specialStorageBins[i] = new SpecialStorageBin(SyrupIngredient.NONE, 0);

        (new SpecialCoffeeTruck("a", storageBins, specialStorageBins)).makeCoffee(coffee, user);
    }
}
