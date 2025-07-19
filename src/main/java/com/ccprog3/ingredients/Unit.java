package com.ccprog3.ingredients;

/**
 * Units of measurement for {@code StorageBin} items
 * 
 * @author Justin Ryan Uy
 */
public enum Unit {
    /**
     * Pieces (pcs)
     */
    PIECES,

    /**
     * Grams (g)
     */
    GRAMS,

    /**
     * Fluid ounce (fl oz)
     */
    FL_OZ;

    @Override
    public String toString() {
        switch (this) {
            case PIECES:
                return "pcs";

            case GRAMS:
                return "g";

            case FL_OZ:
            default:
                return "fl oz";
        }
    }

    /**
     * Converts Fluid ounce to grams
     * 
     * @param oz Number in Fluid ounce
     * @return Number in grams
     */
    public static double flozToG(double oz) {
        return oz * 28.34952;
    }
}
