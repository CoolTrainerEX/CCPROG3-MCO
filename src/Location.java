/**
 * The coordinates of a CoffeeTruck instance
 * @author Justin Ryan Uy
 */
public class Location {
    private double x;
    private double y;
    
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean cmp(Location location) {
        if (x == location.getX() && y == location.getY()) {
            return true;
        }

        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
