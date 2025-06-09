package com.ccprog3;

/**
 * The coordinates of a CoffeeTruck instance
 * @author Justin Ryan Uy
 */
public class Location {
    /**
     * Location x coordinate
     * @author Justin Ryan Uy
     */
    private final double x;

    /**
     * Location y coordinate
     * @author Justin Ryan Uy
     */
    private final double y;

    /**
     * Create a new Location
     * @param x The x coordinate
     * @param y The y coordinate
     * @author Justin Ryan Uy
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Gets the x coordinate
     * @return The x coorinate
     * @author Justin Ryan Uy
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y coordinate
     * @return The y coordinate
     * @author Justin Ryan Uy
     */
    public double getY() {
        return y;
    }

    /**
     * Compare if one location is equal to another
     * @param location The location to compare with
     * @return True if they are equal; false otherwise
     * @author Justin Ryan Uy
     */
    public boolean equals(Location location) {
        return x == location.getX() && y == location.getY();
    }
}
