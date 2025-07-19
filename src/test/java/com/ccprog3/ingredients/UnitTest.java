package com.ccprog3.ingredients;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@code Unit} Enum
 * 
 * @author Justin Ryan Uy
 */
public class UnitTest {
    /**
     * Default constructor
     */
    public UnitTest() {
    }

    /**
     * Tests {@code flozToG} for positive number
     */
    @Test
    public void testPositive() {
        assertEquals(283.4952, Unit.flozToG(10), 0.0001);
    }

    /**
     * Tests {@code flozToG} for zero
     */
    @Test
    public void testZero() {
        assertEquals(0, Unit.flozToG(0), 0.0001);
    }

    /**
     * Tests {@code flozToG} for negative number
     */
    @Test
    public void testNegative() {
        assertEquals(-283.4952, Unit.flozToG(-10), 0.0001);
    }
}
