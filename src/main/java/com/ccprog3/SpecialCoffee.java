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
    private final Espresso espresso;

    /**
     * Custom brew ratio
     * 
     * @author Justin Ryan Uy
     */
    private final int ratio;

    /**
     * Syrup addon
     * 
     * @author Justin Ryan Uy
     */
    private final SyrupIngredient syrup;

    /**
     * Extra shot
     * 
     * @author Justin Ryan Uy
     */
    private final Espresso shot;

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
        this.ratio = 0;
        this.syrup = syrup;
        this.shot = shot;
    }

    /**
     * Special Coffee constructor for Custom brew
     * 
     * @param type     Type of Coffee
     * @param cup      Cup size
     * @param ratio    Custom brew ratio
     * @param espresso Espresso brew
     * @param syrup    Syrup addon
     * @param shot     Extra shot
     * @throws IllegalArgumentException Ingredient is not a cup
     * @author Justin Ryan Uy
     */
    public SpecialCoffee(CoffeeType type, Ingredient cup, int ratio, SyrupIngredient syrup, Espresso shot)
            throws IllegalArgumentException {
        super(type, cup);
        this.espresso = Espresso.NONE;
        this.ratio = ratio;
        this.syrup = syrup;
        this.shot = shot;
    }

    public String toString() {
        return (espresso == Espresso.NONE ? "CUSTOM" : espresso) + " " + type
                + (syrup == SyrupIngredient.NONE ? "" : " " + syrup)
                + (shot == Espresso.NONE ? "" : " " + shot);
    }
}
