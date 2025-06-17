package com.ccprog3;

/**
 * Class to store Coffee details
 * 
 * @author Justin Ryan Uy
 */
public class Coffee {
    /**
     * Type of Coffee product
     * 
     * @author Justin Ryan Uy
     */
    private final CoffeeType type;

    /**
     * Espresso brew
     * 
     * @author Justin Ryan Uy
     */
    private final Espresso espresso;

    /**
     * Coffee price
     * 
     * @author Justin Ryan Uy
     */
    private final float price;

    /**
     * Coffee constructor
     * 
     * @param type     Type of Coffee
     * @param espresso Espresso brew
     * @param price    Coffee price
     * @author Justin Ryan Uy
     */
    public Coffee(CoffeeType type, Espresso espresso, float price) {
        this.type = type;
        this.espresso = espresso;
        this.price = price;
    }

    /**
     * Gets the Coffee Type
     * 
     * @return The Coffee Type
     * @author Justin Ryan Uy
     */
    public CoffeeType getType() {
        return type;
    }

    /**
     * Gets the Espresso brew
     * 
     * @return The Espreso brew
     * @author Justin Ryan Uy
     */
    public Espresso getEspresso() {
        return espresso;
    }

    /**
     * Gets the Coffee price
     * 
     * @return The Coffee price
     * @author Justin Ryan Uy
     */
    public float getPrice() {
        return price;
    }
}
