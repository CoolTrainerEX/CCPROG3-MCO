package com.ccprog3.coffeeTruck;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ccprog3.ingredients.Ingredient;

/**
 * Tests for {@code StorageBin} class
 * 
 * @author Justin Ryan Uy
 */
public class StorageBinTest {
    /**
     * Default constructor
     */
    public StorageBinTest() {
    }

    /**
     * Tests {@code addQuantity} for valid additions
     */
    @Test
    public void testAddQuantityValid() {
        StorageBin storageBin = new StorageBin(Ingredient.LARGE_CUP, 10);

        storageBin.addQuantity(10);
        assertEquals(20, storageBin.getQuantity(), 0.0001);
    }

    /**
     * Tests {@code addQuantity} for additions resulting in zero
     */
    @Test
    public void testAddQuantityValidZero() {
        StorageBin storageBin = new StorageBin(Ingredient.LARGE_CUP, 10);

        storageBin.addQuantity(-storageBin.getQuantity());
        assertEquals(0, storageBin.getQuantity(), 0.0001);
    }

    /**
     * Tests {@code addQuantity} for additions above the max
     */
    @Test(expected = ArithmeticException.class)
    public void testAddQuantityInalidMax() {
        (new StorageBin(Ingredient.LARGE_CUP, 10)).addQuantity(50);
    }

    /**
     * Tests {@code addQuantity} for additions resulting in negative
     */
    @Test(expected = ArithmeticException.class)
    public void testAddQuantityInalidNegative() {
        (new StorageBin(Ingredient.LARGE_CUP, 10)).addQuantity(-50);
    }
}
