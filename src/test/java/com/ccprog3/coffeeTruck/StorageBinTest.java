package com.ccprog3.coffeeTruck;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ccprog3.ingredients.Ingredient;

/**
 * Tests for StorageBin class
 * 
 * @author Justin Ryan Uy
 */
public class StorageBinTest {
    /**
     * Tests addQuantity for valid additions
     * 
     * @author Justin Ryan Uy
     */
    @Test
    public void testAddQuantityValid() {
        StorageBin storageBin = new StorageBin(Ingredient.LARGE_CUP, 10);

        storageBin.addQuantity(10);
        assertEquals(20, storageBin.getQuantity(), 0.0001);
    }

    /**
     * Tests addQuantity for additions resulting in zero
     * 
     * @author Justin Ryan Uy
     */
    @Test
    public void testAddQuantityValidZero() {
        StorageBin storageBin = new StorageBin(Ingredient.LARGE_CUP, 10);

        storageBin.addQuantity(-storageBin.getQuantity());
        assertEquals(0, storageBin.getQuantity(), 0.0001);
    }

    /**
     * Tests addQuantity for additions above the max
     * 
     * @author Justin Ryan Uy
     */
    @Test(expected = ArithmeticException.class)
    public void testAddQuantityInalidMax() {
        (new StorageBin(Ingredient.LARGE_CUP, 10)).addQuantity(50);
    }

    /**
     * Tests addQuantity for additions resulting in negative
     * 
     * @author Justin Ryan Uy
     */
    @Test(expected = ArithmeticException.class)
    public void testAddQuantityInalidNegative() {
        (new StorageBin(Ingredient.LARGE_CUP, 10)).addQuantity(-50);
    }
}
