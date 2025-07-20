package com.ccprog3;

import java.io.Serializable;

/**
 * Money class to validate money amount
 * 
 * @author Justin Ryan Uy
 */
public class Money implements Serializable {
    /**
     * Amount of money
     */
    private final float amount;

    /**
     * Constructor for {@code Money} class
     * 
     * @param amount Amount of money
     * @throws IllegalArgumentException Amount cannot be negative
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
     */
    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("$%.2f", amount);
    }
}
