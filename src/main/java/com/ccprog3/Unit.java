package com.ccprog3;

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
     * Converts grams to Fluid ounce
     * 
     * @param g Number in grams
     * @return Number in Fluid ounce
     * @author Justin Ryan Uy
     */
    public static double gToFloz(double g) {
        return g * 28.34952;
    }
}
