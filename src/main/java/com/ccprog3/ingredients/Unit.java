package com.ccprog3.ingredients;

/**
 * Units of measurement for Storage Bin items
 * 
 * @author Justin Ryan Uy
 */
public enum Unit {
    /**
     * Pieces (pcs)
     * 
     * @author Justin Ryan Uy
     */
    PIECES,

    /**
     * Grams (g)
     * 
     * @author Justin Ryan Uy
     */
    GRAMS,

    /**
     * Fluid ounce (fl oz)
     * 
     * @author Justin Ryan Uy
     */
    FL_OZ;

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
     * @author Justin Ryan Uy
     */
    public static double flozToG(double oz) {
        return oz * 28.34952;
    }
}
