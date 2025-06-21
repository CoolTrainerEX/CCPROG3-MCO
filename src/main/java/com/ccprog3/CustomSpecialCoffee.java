package com.ccprog3;

/**
 * Special Coffee with a custom brew ratio
 * 
 * @author Justin Ryan Uy
 */
public class CustomSpecialCoffee extends SpecialCoffee {
    /**
     * Custom brew ratio
     * 
     * @author Justin Ryan Uy
     */
    private final int ratio;

    /**
     * Constructor for Special Coffee with custom brew ratio
     * 
     * @param type  Type of Coffee
     * @param cup   Cup size
     * @param ratio Custom brew ratio of water to coffee (ex. 2 = 1:2)
     * @param shot  Extra shot
     * @throws IllegalArgumentException Ingredient is not a cup
     * @author Justin Ryan Uy
     */
    public CustomSpecialCoffee(CoffeeType type, Ingredient cup, int ratio, SyrupIngredient syrup, Espresso shot)
            throws IllegalArgumentException {
        super(type, cup, Espresso.NONE, syrup, shot);
        this.ratio = ratio;
    }

    public String toString() {
        return "CUSTOM " + type + (syrup == SyrupIngredient.NONE ? "" : " " + syrup)
                + (shot == Espresso.NONE ? "" : " " + shot);
    }
}
