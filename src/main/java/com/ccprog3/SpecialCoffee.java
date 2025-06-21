package com.ccprog3;

/**
 * Special Coffee from Special Coffee Truck
 * 
 * @author Justin Ryan Uy
 */
public class SpecialCoffee extends Coffee {
    /**
     * Espresso brew
     * 
     * @author Justin Ryan Uy
     */
    protected final Espresso espresso;

    /**
     * Syrup addon
     * 
     * @author Justin Ryan Uy
     */
    protected final SyrupIngredient syrup;

    /**
     * Extra shot
     * 
     * @author Justin Ryan Uy
     */
    protected final Espresso shot;

    /**
     * Special Coffee constructor
     * 
     * @param type     Type of Coffee
     * @param cup      Cup size
     * @param espresso Espresso brew
     * @param syrup    Syrup addon
     * @param shot     Extra shot
     * @throws IllegalArgumentException Ingredient is not a cup
     * @author Justin Ryan Uy
     */
    public SpecialCoffee(CoffeeType type, Ingredient cup, Espresso espresso, SyrupIngredient syrup, Espresso shot)
            throws IllegalArgumentException {
        super(type, cup);
        this.espresso = espresso;
        this.syrup = syrup;
        this.shot = shot;
    }

    public String toString() {
        return espresso + " " + type + (syrup == SyrupIngredient.NONE ? "" : " " + syrup)
                + (shot == Espresso.NONE ? "" : " " + shot);
    }
}
