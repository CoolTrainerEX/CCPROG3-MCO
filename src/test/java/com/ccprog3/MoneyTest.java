package com.ccprog3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for Money Class
 * 
 * @author Justin Ryan Uy
 */
public class MoneyTest {
    /**
     * Tests if constructor accepts positive number
     * 
     * @author Justin Ryan Uy
     */
    @Test
    public void testPositive() {
        assertEquals(1, (new Money(1)).getAmount(), 0.0001);
    }

    /**
     * Tests if constructor accepts zero
     * 
     * @author Justin Ryan Uy
     */
    @Test
    public void testZero() {
        assertEquals(0, (new Money(0)).getAmount(), 0.0001);
    }

    /**
     * Tests if constructor accepts negative number
     * 
     * @author Justin Ryan Uy
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegative() {
        new Money(-1);
    }
}
