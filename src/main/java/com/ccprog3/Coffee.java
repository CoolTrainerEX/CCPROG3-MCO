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
    protected final CoffeeType type;

    /**
     * Cup size of the Coffee
     * 
     * @author Justin Ryan Uy
     */
    protected final Ingredient cup;

    /**
     * Coffee class constructor
     * 
     * @param type Type of Coffee
     * @param cup  Cup size
     * @throws IllegalArgumentException Ingredient is not a cup
     * @author Justin Ryan Uy
     */
    public Coffee(CoffeeType type, Ingredient cup) throws IllegalArgumentException {
        switch (cup) {
            case SMALL_CUP:
            case MEDIUM_CUP:
            case LARGE_CUP:
                break;

            default:
                throw new IllegalArgumentException("Ingredient is not a cup");
        }

        this.type = type;
        this.cup = cup;
    }

    public String toString() {
        return cup + " " + type;
    }
}
