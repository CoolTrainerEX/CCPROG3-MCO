package com.ccprog3;

/**
 * The coordinates of a CoffeeTruck instance
 * @author Justin Ryan Uy
 */
public class Location {
    private final double x;
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
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y coordinate
     * @return The y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Compare if one location is equal to another
     * @param location The location to compare with
     * @return True if they are equal; false otherwise
     */
    public boolean equals(Location location) {
        return x == location.getX() && y == location.getY();
    }
}
