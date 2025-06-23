package com.ccprog3;

/**
 * Money class to validate money amount
 * 
 * @author Justin Ryan Uy
 */
public class Money {
    /**
     * Amount of money
     * 
     * @author Justin Ryan Uy
     */
    private final float amount;

    /**
     * Constructor for Money class
     * 
     * @param amount Amount of money
     * @throws IllegalArgumentException Amount cannot be negative
     * @author Justin Ryan Uy
     */
    public Money(float amount) throws IllegalArgumentException {
        if (amount < 0)
            throw new IllegalArgumentException("Amount cannot be negative");

        this.amount = amount;
    }

    /**
     * Gets the amount of money
     * 
     * @return The amount of money
     * @author Justin Ryan Uy
     */
    public float getAmount() {
        return amount;
    }

    public String toString() {
        return String.format("$%.2f", amount);
    }
}
