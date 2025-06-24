package com.ccprog3.ingredients;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for Unit Enum
 * 
 * @author Justin Ryan Uy
 */
public class UnitTest {
    /**
     * Tests flozToG for positive number
     * 
     * @author Justin Ryan Uy
     */
    @Test
    public void testPositive() {
        assertEquals(283.4952, Unit.flozToG(10), 0.0001);
    }

    /**
     * Tests flozToG for zero
     * 
     * @author Justin Ryan Uy
     */
    @Test
    public void testZero() {
        assertEquals(0, Unit.flozToG(0), 0.0001);
    }

    /**
     * Tests flozToG for negative number
     * 
     * @author Justin Ryan Uy
     */
    @Test
    public void testNegative() {
        assertEquals(-283.4952, Unit.flozToG(-10), 0.0001);
    }
}
