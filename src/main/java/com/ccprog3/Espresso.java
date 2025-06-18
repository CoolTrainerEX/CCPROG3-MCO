package com.ccprog3;

/**
 * Types of Espresso brews
 * 
 * @author Justin Ryan Uy
 */
public enum Espresso {
    /**
     * Standard brew 1:18
     * 
     * @author Justin Ryan Uy
     */
    STANDARD,

    /**
     * Strong brew 1:15
     * 
     * @author Justin Ryan Uy
     */
    STRONG,

    /**
     * Light brew 1:20
     * 
     * @author Justin Ryan Uy
     */
    LIGHT,

    /**
     * Custom brew 1:? or No extra shot
     * 
     * @author Justin Ryan Uy
     */
    NONE;

    /**
     * Gets the ratio of Water in percent
     * 
     * @return The ratio of Water in percent
     * @throws IllegalArgumentException Custom brew cannot have a ratio
     * @author Justin Ryan Uy
     */
    public double getRatio() throws IllegalArgumentException {
        switch (this) {
            case STANDARD:
                return 18 / 19;

            case STRONG:
                return 15 / 16;

            case LIGHT:
                return 20 / 21;

            default:
                throw new IllegalArgumentException("Custom brew cannot have a ratio");
        }
    }
}
